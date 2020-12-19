package raidClicker.listeners;


import raidClicker.MouseActions;
import raidClicker.StartStopListener;
import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadSecondsToClickText;
import raidClicker.contentPayloads.PayloadSecondsToStopText;
import raidClicker.contentPayloads.PayloadStartStopTextToChange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static raidClicker.contentPayloads.helpers.StringHelpers.tryParseOrDefault;

public final class ClickingListener implements ActionListener, ResettableTimerListener {

    int secondsPassed = 0;
    String textWhenRunningIndefinitely;
    private Integer runningTime;
    private Timer clickingTimer;
    private Integer clickInSeconds;
    private MouseActions mouseActions;
    private final StartStopListener startStopListener;
    private final JTextField clickInSecondsJTF;
    private final JTextField runningTimeJTF;

    public ClickingListener(
            Timer clickingTimer,
            MouseActions mouseActions,
            StartStopListener startStopListener,
            JTextField clickInSecondsJTF,
            JTextField runningTimeJTF) {
        this.clickingTimer = clickingTimer;
        this.mouseActions = mouseActions;
        this.startStopListener = startStopListener;
        this.clickInSecondsJTF = clickInSecondsJTF;
        this.runningTimeJTF = runningTimeJTF;
        resetTimer();
    }

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
                startStopListener.changeRunningStatus();
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

    @Override
    public void resetTimer() {
        this.clickInSeconds = tryParseOrDefault(clickInSecondsJTF.getText(), 10);
        this.runningTime = tryParseOrDefault(runningTimeJTF.getText(), null);
        secondsPassed = 0;
    }
}
