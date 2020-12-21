package raidClicker.listeners;

import raidClicker.MouseActions;
import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadSecondsToClickText;
import raidClicker.uniqueComponentHandlers.LocationHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.format;

public final class MouseLocationListener implements ActionListener, ResettableTimerListener {

    private int seconds;
    private MouseActions mouseActions;
    private Timer mouseLocationTimer;
    private Timer clickingTimer;

    public MouseLocationListener(MouseActions mouseActions, Timer mouseLocationTimer, Timer clickingTimer) {
        this.mouseActions = mouseActions;
        this.mouseLocationTimer = mouseLocationTimer;
        this.clickingTimer = clickingTimer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText(format("%d sec > ML", 5 - seconds)));
        if (seconds == 5) {
            mouseActions.takeMouseLocationFromScreen();
            LocationHandler.setLocation(mouseActions.getMouseLocation());
            ComponentManager.addPayloadToConsume(new PayloadSecondsToClickText("Took mouse location"));
            mouseLocationTimer.stop();
            mouseActions.doubleClick();
            clickingTimer.start();
        }
    }

    @Override
    public void resetTimer() {
        seconds = 0;
    }
}
