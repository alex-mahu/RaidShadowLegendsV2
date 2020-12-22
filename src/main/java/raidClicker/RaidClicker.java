package raidClicker;

import raidClicker.components.NoComponentRequired;
import raidClicker.components.LocationComponent;
import raidClicker.contentPayloads.*;
import raidClicker.contentPayloads.handlers.ButtonStartStopTextChanger;
import raidClicker.contentPayloads.handlers.LabelSecondsToClickTextChanger;
import raidClicker.contentPayloads.handlers.LabelStopTextChanger;
import raidClicker.contentPayloads.handlers.MusicHandler;
import raidClicker.uniqueComponentHandlers.LocationHandler;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static raidClicker.constants.ButtonConstants.COOL_GREEN;
import static raidClicker.constants.ButtonConstants.START;

public class RaidClicker {

    JFrame frame;
    JButton startStop;
    JTextField secondsToWaitUntilClick;
    JTextField secondsToRun;
    JLabel secondsToClickLabel;
    JLabel remainingTimeInSecondsLabel;
    LocationComponent locationComponent;

    public RaidClicker() {
        frame = new JFrame("Clicker");
        final GridLayout frameLayout = new GridLayout(0, 1);
        frame.setLayout(frameLayout);
        startStop = new JButton(START);
        startStop.setFocusPainted(false);
        startStop.setBackground(COOL_GREEN);
        frame.add(startStop, BorderLayout.NORTH);

        secondsToWaitUntilClick = new JTextField();
        secondsToWaitUntilClick.setHorizontalAlignment(JTextField.CENTER);
        secondsToRun = new JTextField();
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

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUiHandlers();

        new Timer(25, e -> ComponentManager.checkAndConsumePayloads()).start();
    }

    public static void main(String[] args) {
        new RaidClicker();
    }

    private void initializeUiHandlers() {
        ComponentManager.registerComponentHandler(PayloadStartStopTextToChange.class, new ButtonStartStopTextChanger(startStop));
        ComponentManager.registerComponentHandler(PayloadSecondsToClickText.class, new LabelSecondsToClickTextChanger(secondsToClickLabel));
        ComponentManager.registerComponentHandler(PayloadSecondsToStopText.class, new LabelStopTextChanger(remainingTimeInSecondsLabel));
        ComponentManager.registerComponentHandler(PayloadMusic.class, new MusicHandler(NoComponentRequired.INSTANCE));
    }
}
