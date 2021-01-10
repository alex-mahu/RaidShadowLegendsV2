package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadSecondsToRunLabel;

import javax.swing.*;

public final class LabelStopTextChanger extends ComponentHandler<JLabel, PayloadSecondsToRunLabel> {

    public LabelStopTextChanger(JLabel component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToRunLabel payload) {
        System.out.println("[LabelStopTextChanger] " + payload.getLabelText());
        component.setText(payload.getLabelText());
    }


}
