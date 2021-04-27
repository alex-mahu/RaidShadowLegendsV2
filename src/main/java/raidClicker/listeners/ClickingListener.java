package raidClicker.listeners;

import raidClicker.ApplicationStatus;
import raidClicker.MouseActions;
import raidClicker.StartStopListener;
import raidClicker.contentPayloads.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static raidClicker.constants.ButtonConstants.START;
import static raidClicker.contentPayloads.handlers.MusicHandler.Songs.MUSHROOM;
import static raidClicker.helpers.StringHelpers.tryParseOrDefault;

public final class ClickingListener implements ActionListener, ResettableTimerListener {

    private final StartStopListener startStopListener;
    private final JTextField clickInSecondsJTF;
    private final JTextField runningTimeJTF;
    private Integer secondsPassed = 0;
    private String textWhenRunningIndefinitely;
    private Integer runningTime;
    private final Timer clickingTimer;
    private Integer clickInSeconds;
    private final MouseActions mouseActions;
    private int clicksRemaining;

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
        clicksRemaining = ApplicationStatus.getNumberOfRuns();
        resetTimer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        secondsPassed++;
        if ((isNull(runningTime) || isNull(textWhenRunningIndefinitely)) && (!ApplicationStatus.usesNumberOfRuns())) {
            textWhenRunningIndefinitely = "Running indefinitely.";
            ComponentManager.addPayloadToConsume(new PayloadSecondsToRunLabel(textWhenRunningIndefinitely));
        } else {
            if ((ApplicationStatus.isSmart() && ApplicationStatus.usesNumberOfRuns() && clicksRemaining == 0) || (secondsPassed.equals(runningTime))) {
                ComponentManager.addPayloadToConsume(new PayloadSecondsToRunLabel("Finished Run"));
                ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange(START));
                ComponentManager.addPayloadToConsume(new PayloadSecondsToClickLabel("Click START to run."));
                startStopListener.changeRunningStatus();
                secondsPassed = 0;
                clickingTimer.stop();
                ComponentManager.addPayloadToConsume(new PayloadMusic(MUSHROOM));
                return;
            } else {
                if (ApplicationStatus.usesNumberOfRuns()) {
                    ComponentManager.addPayloadToConsume(new PayloadSecondsToRunLabel(format("%d runs until stop", clicksRemaining)));
                } else {
                    ComponentManager.addPayloadToConsume(new PayloadSecondsToRunLabel(format("%d seconds until stop", runningTime - secondsPassed)));
                }
            }
        }

        int secondsToClick = (secondsPassed % clickInSeconds);

        if (secondsToClick == 0) {
            if (!ApplicationStatus.isSmart()) {
                performClick();
                return;
            }

            if (mouseActions.shouldPerformClick()) {
                performClick();
                if (ApplicationStatus.usesNumberOfRuns()) {
                    clicksRemaining--;
                }
            } else {
                ComponentManager.addPayloadToConsume(new PayloadSecondsToClickLabel(">>>SKIP<<<"));
            }
        } else {
            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickLabel(format("Next click: %d sec", (clickInSeconds - secondsToClick))));
        }
    }

    private void performClick() {
        ComponentManager.addPayloadToConsume(new PayloadSecondsToClickLabel(">>>CLICKED<<<"));
        mouseActions.click();
    }

    @Override
    public void resetTimer() {
        this.clickInSeconds = tryParseOrDefault(clickInSecondsJTF.getText(), 10);
        this.runningTime = tryParseOrDefault(runningTimeJTF.getText(), null);
        secondsPassed = 0;
    }
}
