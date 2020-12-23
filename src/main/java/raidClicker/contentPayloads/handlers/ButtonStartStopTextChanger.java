package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.PayloadStartStopTextToChange;

import javax.swing.*;
import java.awt.*;

import static raidClicker.constants.ButtonConstants.*;

public final class ButtonStartStopTextChanger extends ComponentHandler<JButton, PayloadStartStopTextToChange> {

    public ButtonStartStopTextChanger(JButton component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadStartStopTextToChange payload) {
        component.setText(payload.getButtonText());
        if (payload.getButtonText().equals(START)) {
            component.setBackground(COOL_GREEN);
            component.revalidate();
            return;
        }

        if (payload.getButtonText().equals(STOP)) {
            component.setBackground(COOL_ORANGE);
            component.revalidate();
            return;
        }
    }


}
