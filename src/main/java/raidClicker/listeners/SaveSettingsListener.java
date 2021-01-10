package raidClicker.listeners;

import raidClicker.helpers.SettingsHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SaveSettingsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsHelper.saveSettingsToFile();
    }
}
