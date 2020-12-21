package raidClicker;

import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadSecondsToClickText;
import raidClicker.contentPayloads.PayloadSecondsToStopText;
import raidClicker.contentPayloads.PayloadStartStopTextToChange;
import raidClicker.exceptions.LocationNotParsable;
import raidClicker.listeners.ClickingListener;
import raidClicker.listeners.MouseLocationListener;
import raidClicker.uniqueComponentHandlers.LocationHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static raidClicker.contentPayloads.helpers.ListenersHelper.addOneListener;
import static raidClicker.contentPayloads.helpers.ListenersHelper.resetListenerForTimer;

public final class StartStopListener implements ActionListener {

    private final MouseActions mouseActions;
    private final Timer clickingTimer;
    private final Timer mouseLocationTimer;
    private JTextField clickInSecondsJTF;
    private JTextField runningTimeJTF;
    private Boolean isRunning = false;

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
        resetListenerForTimer(mouseLocationTimer);
        resetListenerForTimer(clickingTimer);
        System.out.println("[StartStopListener] isRunning = " + isRunning);
        if (!isRunning) {
            getMousePosition();
            isRunning = true;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange("STOP"));
            addOneListener(clickingTimer, new ClickingListener(clickingTimer, mouseActions, this, clickInSecondsJTF, runningTimeJTF));
        } else {
            isRunning = false;
            ComponentManager.addPayloadToConsume(new PayloadStartStopTextToChange("START"));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToStopText("Run stopped by user."));
            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText("STOPPED!"));
            clickingTimer.stop();
        }
    }

    private void getMousePosition() {
        try {
            mouseActions.setLocation(LocationHandler.getLocation());
            mouseActions.doubleClick();
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
