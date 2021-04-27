package raidClicker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import raidClicker.ApplicationStatus;

public final class SmartListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationStatus.setSmart(((JCheckBox) e.getSource()).isSelected());
    }
}
