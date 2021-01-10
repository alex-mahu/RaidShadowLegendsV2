package raidClicker.components;

import raidClicker.listeners.LoadSettingsListener;
import raidClicker.listeners.SaveSettingsListener;

import javax.swing.*;
import java.awt.*;

public final class LoadSaveSettingsComponent extends JPanel {

    private final JButton save;
    private final JButton load;

    public LoadSaveSettingsComponent() {
        save = new JButton("SAVE");
        save.addActionListener(new SaveSettingsListener());
        load = new JButton("LOAD");
        load.addActionListener(new LoadSettingsListener());
        this.setLayout(new GridLayout(1,2));
        this.add(save);
        this.add(load);
        this.setVisible(true);
    }


}
