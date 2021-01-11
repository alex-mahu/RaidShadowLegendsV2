package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadSecondsToClickLabel;

import javax.swing.*;

public final class LabelSecondsToClickTextChanger extends ComponentHandler<JLabel, PayloadSecondsToClickLabel> {

    public LabelSecondsToClickTextChanger(JLabel component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToClickLabel payload) {
        System.out.println("[LabelSecondsToClickTextChanger] " + payload.getLabelText());
        component.setText(payload.getLabelText());
    }


}
