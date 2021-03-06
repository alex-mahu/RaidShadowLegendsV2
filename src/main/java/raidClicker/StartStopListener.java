package raidClicker;

import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadSecondsToClickLabel;
import raidClicker.contentPayloads.PayloadSecondsToRunLabel;
import raidClicker.contentPayloads.PayloadStartStopTextToChange;
import raidClicker.exceptions.LocationNotParsable;
import raidClicker.listeners.ClickingListener;
import raidClicker.listeners.MouseLocationListener;
import raidClicker.uniqueComponentHandlers.LocationHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static raidClicker.constants.ButtonConstants.START;
import static raidClicker.constants.ButtonConstants.STOP;
import static raidClicker.helpers.ListenersHelper.addOneListener;
import static raidClicker.helpers.ListenersHelper.resetListenerForTimer;

public final class StartStopListener implements ActionListener {

    private final MouseActions mouseActions;
    private final Timer clickingTimer;
    private final Timer mouseLocationTimer;
    private final JTextField clickInSecondsJTF;
    private final JTextField runningTimeJTF;
    private final JTextField numberOfRuns;
    private Boolean isRunning = false;

    public StartStopListener(JTextField clickInSeconds, JTextField runningTime, JTextField numberOfRuns) {
        this.clickInSecondsJTF = clickInSeconds;
        this.runningTimeJTF = runningTime;
        this.numberOfRuns = numberOfRuns;
        mouseActions = new MouseActions();
        clickingTimer = new Timer(1000, null);
        mouseLocationTimer = new Timer(1000, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickingTimer.stop();
        mouseLocationTimer.stop();
        resetListenerForTimer(mouseLocationTimer);
        resetListenerForTimer(clickingTimer);
        System.out.println("[StartStopListener] isRunning = " + isRunning);
        if (!isRunning) {
            ApplicationStatus.setNumberOfRuns(numberOfRuns.getText());
            getMousePosition();
            isRunning = true;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange(STOP));
            addOneListener(clickingTimer, new ClickingListener(clickingTimer, mouseActions, this, clickInSecondsJTF, runningTimeJTF));
        } else {
            isRunning = false;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange(START));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToRunLabel("Run stopped by user."));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickLabel("STOPPED!"));
            clickingTimer.stop();
        }
    }

    private void getMousePosition() {
        try {
            mouseActions.setLocation(LocationHandler.getLocation());
            mouseActions.click();
            clickingTimer.start();
        } catch (LocationNotParsable ex) {
            addOneListener(mouseLocationTimer, new MouseLocationListener(mouseActions, mouseLocationTimer, clickingTimer));
            mouseLocationTimer.start();
        }
    }

    public void changeRunningStatus() {
        isRunning = !isRunning;
    }
}
