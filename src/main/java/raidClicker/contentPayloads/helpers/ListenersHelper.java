package raidClicker.contentPayloads.helpers;

import raidClicker.listeners.ResettableTimerListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public final class ListenersHelper {

    private ListenersHelper() {
    }

    public static void addOneListener(Timer timer, ActionListener listener) {
        if (timer.getActionListeners().length == 0) {
            timer.addActionListener(listener);
        }
    }

    public static void resetListenerForTimer(Timer timer) {
        if (canResetTimer(timer)) {
            ((ResettableTimerListener) timer.getActionListeners()[0]).resetTimer();
        }
    }

    private static boolean canResetTimer(Timer timer) {
        return timer.getActionListeners().length > 0;
    }
}
