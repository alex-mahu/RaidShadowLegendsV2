package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadSecondsToClickText;

import javax.swing.*;

public final class LabelSecondsToClickTextChanger extends ComponentHandler<JLabel, PayloadSecondsToClickText> {

    public LabelSecondsToClickTextChanger(JLabel component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToClickText payload) {
        System.out.println("[LabelSecondsToClickTextChanger] " + payload.getLabelText());
        component.setText(payload.getLabelText());
    }


}
