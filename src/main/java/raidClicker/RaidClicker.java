package raidClicker;

import raidClicker.contentPayloads.PayloadStartStopTextToChange;
import raidClicker.contentPayloads.ComponentManager;
import raidClicker.contentPayloads.PayloadSecondsToClickText;
import raidClicker.contentPayloads.PayloadSecondsToStopText;
import raidClicker.contentPayloads.handlers.ButtonStartStopTextChanger;
import raidClicker.contentPayloads.handlers.LabelSecondsToClickTextChanger;
import raidClicker.contentPayloads.handlers.LabelStopTextChanger;

import javax.swing.*;
import java.awt.*;

public class RaidClicker {

    JFrame frame;
    JButton startStop;
    JTextField secondsToWaitUntilClick;
    JTextField secondsToRun;
    JLabel secondsToClickLabel;
    JLabel remainingTimeInSecondsLabel;

    public RaidClicker() {
        frame = new JFrame("Click the shit of it!");
        final GridLayout layout = new GridLayout(3, 1);
        layout.setHgap(5);
        layout.setVgap(5);
        frame.setLayout(layout);
        startStop = new JButton("START");
        frame.add(startStop, BorderLayout.NORTH);
        secondsToWaitUntilClick = new JTextField();
        secondsToWaitUntilClick.setHorizontalAlignment(JTextField.CENTER);
        secondsToRun = new JTextField();
        secondsToRun.setHorizontalAlignment(JTextField.CENTER);
        startStop.addActionListener(new StartStopListener(secondsToWaitUntilClick, secondsToRun));

        JPanel centerPanel = new JPanel();
        final GridLayout inputLayouts = new GridLayout(2, 2);
        centerPanel.setLayout(inputLayouts);
        centerPanel.add(new JLabel("SECONDS FOR CLICK:"), BorderLayout.EAST);
        centerPanel.add(secondsToWaitUntilClick, BorderLayout.WEST);

        centerPanel.setLayout(inputLayouts);
        centerPanel.add(new JLabel("DURATION IN SEC:"), BorderLayout.EAST);
        centerPanel.add(secondsToRun, BorderLayout.WEST);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel runInformationPanel = new JPanel();
        final GridLayout informationLayouts = new GridLayout(2, 1);
        runInformationPanel.setLayout(informationLayouts);
        secondsToClickLabel = new JLabel("OFF", SwingConstants.CENTER);
        remainingTimeInSecondsLabel = new JLabel("Not started.", SwingConstants.CENTER);
        runInformationPanel.add(secondsToClickLabel);
        runInformationPanel.add(remainingTimeInSecondsLabel);

        frame.add(runInformationPanel, BorderLayout.SOUTH);
        frame.setSize(300, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        initializeUiHandlers();

        new Timer(1, e -> ComponentManager.checkAndConsumePayloads()).start();
    }

    private void initializeUiHandlers() {
        ComponentManager.registerComponentHandler(PayloadStartStopTextToChange.class, new ButtonStartStopTextChanger(startStop));
        ComponentManager.registerComponentHandler(PayloadSecondsToClickText.class, new LabelSecondsToClickTextChanger(secondsToClickLabel));
        ComponentManager.registerComponentHandler(PayloadSecondsToStopText.class, new LabelStopTextChanger(remainingTimeInSecondsLabel));
    }

    public static void main(String[] args) {
        new RaidClicker();
    }
}
