package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadStartStopTextToChange;

import javax.swing.*;

public final class ButtonStartStopTextChanger extends ComponentHandler<JButton, PayloadStartStopTextToChange> {

    public ButtonStartStopTextChanger(JButton component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadStartStopTextToChange payload) {
        System.out.println("[ButtonStartStopTextChanger] " + payload.getButtonText());
        component.setText(payload.getButtonText());
    }


}
