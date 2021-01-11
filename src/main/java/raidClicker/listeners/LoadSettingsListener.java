package raidClicker.listeners;

import raidClicker.helpers.SettingsHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LoadSettingsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsHelper.loadSettingsFromFile();
    }
}
