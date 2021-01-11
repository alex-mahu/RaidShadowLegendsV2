package raidClicker.contentPayloads.handlers;

import raidClicker.components.SecondsToRun;
import raidClicker.contentPayloads.PayloadSecondsToRunTextBox;

public final class SecondsToRunChanger extends ComponentHandler<SecondsToRun, PayloadSecondsToRunTextBox> {

    public SecondsToRunChanger(SecondsToRun component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToRunTextBox payload) {
        component.setText(payload.getSecondsToRun());
        component.validate();
    }
}
