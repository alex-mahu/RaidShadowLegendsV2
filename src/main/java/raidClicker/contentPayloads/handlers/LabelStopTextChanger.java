package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadSecondsToStopText;

import javax.swing.*;

public final class LabelStopTextChanger extends ComponentHandler<JLabel, PayloadSecondsToStopText> {

    public LabelStopTextChanger(JLabel component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToStopText payload) {
        System.out.println("[LabelStopTextChanger] " + payload.getLabelText());
        component.setText(payload.getLabelText());
    }


}
