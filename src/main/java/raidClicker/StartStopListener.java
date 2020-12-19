package raidClicker;

import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadStartStopTextToChange;
import raidClicker.contentPayloads.PayloadSecondsToClickText;
import raidClicker.contentPayloads.PayloadSecondsToStopText;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static raidClicker.contentPayloads.helpers.StringHelpers.tryParseOrDefault;

public final class StartStopListener implements ActionListener {

    private Integer clickInSeconds;
    private Integer runningTime;
    private JTextField clickInSecondsJTF;
    private JTextField runningTimeJTF;
    private final MouseActions mouseActions;
    private final Timer clickingTimer;
    private final Timer mouseLocationTimer;
    private boolean isRunning = false;

    public StartStopListener(JTextField clickInSeconds, JTextField runningTime) {
        this.clickInSecondsJTF = clickInSeconds;
        this.runningTimeJTF = runningTime;
        mouseActions = new MouseActions();
        clickingTimer = new Timer(1000, null);
        mouseLocationTimer = new Timer(1000, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickingTimer.stop();
        mouseLocationTimer.stop();
        this.clickInSeconds = tryParseOrDefault(clickInSecondsJTF.getText(), 10);
        this.runningTime = tryParseOrDefault(runningTimeJTF.getText(), null);
        System.out.println("[StartStopListener] isRunning = " + isRunning);
        if (!isRunning) {
            getMousePosition();
            isRunning = true;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange("STOP"));
            clickingTimer.addActionListener(new ActionListener() {
                int secondsPassed = 0;
                String textWhenRunningIndefinitely;
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsPassed++;
                    if (isNull(runningTime) || isNull(textWhenRunningIndefinitely)) {
                        textWhenRunningIndefinitely = "Running indefinitely.";
                        ComponentManager.addPayloadToConsume(new PayloadSecondsToStopText(textWhenRunningIndefinitely));
                    } else {
                        if (secondsPassed == runningTime) {
                            ComponentManager.addPayloadToConsume(new PayloadSecondsToStopText("Finished Running time"));
                            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange("START"));
                            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText("Click START to run."));
                            isRunning = false;
                            secondsPassed = 0;
                            clickingTimer.stop();
                            return;
                        } else {
                            ComponentManager.addPayloadToConsume(new PayloadSecondsToStopText(format("%d seconds until stop", runningTime - secondsPassed)));
                        }
                    }

                    int secondsToClick = (secondsPassed % clickInSeconds);

                    if (secondsToClick == 0) {
                        ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText(">>>CLICKED<<<"));
                        mouseActions.doubleClick();
                    } else {
                        ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText(format("Next click: %d sec", (clickInSeconds - secondsToClick))));
                    }
                }
            });
        } else {
            isRunning = false;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange("START"));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToStopText("Run stopped by user."));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText("STOPPED!"));
            clickingTimer.stop();
        }
    }

    private void getMousePosition() {
        mouseLocationTimer.addActionListener(new ActionListener() {

            private int seconds;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText(format("%d sec > ML", 5 - seconds)));
                if (seconds == 5) {
                    mouseActions.getMouseLocation();
                    ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText("Took mouse location"));
                    mouseLocationTimer.stop();
                    seconds = 0;
                    clickingTimer.start();
                }
            }
        });
        mouseLocationTimer.start();
    }
}
