package raidClicker;

import raidClicker.components.*;
import raidClicker.contentPayloads.*;
import raidClicker.contentPayloads.handlers.*;
import raidClicker.uniqueComponentHandlers.LocationHandler;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static raidClicker.constants.ButtonConstants.COOL_GREEN;
import static raidClicker.constants.ButtonConstants.START;
import static raidClicker.helpers.SettingsHelper.createSettingsFileIfNotExists;
import static raidClicker.helpers.SettingsHelper.loadSettingsFromFile;

public class RaidClicker {

    JFrame frame;
    JButton startStop;
    SecondsToWaitForClick secondsToWaitUntilClick;
    SecondsToRun secondsToRun;
    JLabel secondsToClickLabel;
    JLabel remainingTimeInSecondsLabel;
    LocationComponent locationComponent;
    LoadSaveSettingsComponent loadSaveSettingsComponent;

    public RaidClicker() {
        frame = new JFrame("Clicker");
        final GridLayout frameLayout = new GridLayout(0, 1);
        frame.setLayout(frameLayout);
        startStop = new JButton(START);
        startStop.setFocusPainted(false);
        startStop.setOpaque(true);
        startStop.setBorderPainted(false);
        startStop.setBackground(COOL_GREEN);
        frame.add(startStop, BorderLayout.NORTH);

        secondsToWaitUntilClick = new SecondsToWaitForClick();
        secondsToWaitUntilClick.setHorizontalAlignment(JTextField.CENTER);
        secondsToRun = new SecondsToRun();
        secondsToRun.setHorizontalAlignment(JTextField.CENTER);
        startStop.addActionListener(new StartStopListener(secondsToWaitUntilClick, secondsToRun));

        JPanel centerPanel = new JPanel();
        final GridLayout inputLayouts = new GridLayout(0, 2);
        locationComponent = new LocationComponent();
        frame.add(locationComponent);
        LocationHandler.loadComponent(locationComponent);

        centerPanel.setLayout(inputLayouts);
        centerPanel.add(new JLabel("Click (sec):"), BorderLayout.EAST);
        centerPanel.add(secondsToWaitUntilClick, BorderLayout.WEST);

        centerPanel.setLayout(inputLayouts);
        centerPanel.add(new JLabel("Duration (sec):"), BorderLayout.EAST);
        centerPanel.add(secondsToRun, BorderLayout.WEST);
        centerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel runInformationPanel = new JPanel();
        final GridLayout informationLayouts = new GridLayout(0, 1);
        runInformationPanel.setLayout(informationLayouts);
        secondsToClickLabel = new JLabel("OFF", SwingConstants.CENTER);
        remainingTimeInSecondsLabel = new JLabel("Not started.", SwingConstants.CENTER);
        runInformationPanel.add(secondsToClickLabel);
        runInformationPanel.add(remainingTimeInSecondsLabel);
        runInformationPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        frame.add(runInformationPanel, BorderLayout.SOUTH);
        loadSaveSettingsComponent = new LoadSaveSettingsComponent();
        frame.add(loadSaveSettingsComponent, BorderLayout.SOUTH);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUiHandlers();

        createSettingsFileIfNotExists();
        loadSettingsFromFile();

        new Timer(25, e -> ComponentManager.checkAndConsumePayloads()).start();
    }

    public static void main(String[] args) {
        new RaidClicker();
    }

    private void initializeUiHandlers() {
        ComponentManager.registerComponentHandler(PayloadStartStopTextToChange.class, new ButtonStartStopTextChanger(startStop));
        ComponentManager.registerComponentHandler(PayloadSecondsToClickLabel.class, new LabelSecondsToClickTextChanger(secondsToClickLabel));
        ComponentManager.registerComponentHandler(PayloadSecondsToRunLabel.class, new LabelStopTextChanger(remainingTimeInSecondsLabel));
        ComponentManager.registerComponentHandler(PayloadMusic.class, new MusicHandler(NoComponentRequired.INSTANCE));
        ComponentManager.registerComponentHandler(PayloadUpdateUiSettings.class, new SettingsToUiChanger(loadSaveSettingsComponent));
        ComponentManager.registerComponentHandler(PayloadLocation.class, new LocationChanger(locationComponent));
        ComponentManager.registerComponentHandler(PayloadSecondsToClickTextBox.class, new SecondsToClickChanger(secondsToWaitUntilClick));
        ComponentManager.registerComponentHandler(PayloadSecondsToRunTextBox.class, new SecondsToRunChanger(secondsToRun));
        ComponentManager.registerComponentHandler(PayloadUpdateSettings.class, new CopySettingsToStaticObject(new SettingsWrapperComponent(secondsToWaitUntilClick, secondsToRun, locationComponent)));
    }
}
