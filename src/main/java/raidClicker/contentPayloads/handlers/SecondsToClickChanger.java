package raidClicker.contentPayloads.handlers;

import raidClicker.components.SecondsToWaitForClick;
import raidClicker.contentPayloads.PayloadSecondsToClickTextBox;

public final class SecondsToClickChanger extends ComponentHandler<SecondsToWaitForClick, PayloadSecondsToClickTextBox> {

    public SecondsToClickChanger(SecondsToWaitForClick component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadSecondsToClickTextBox payload) {
        component.setText(payload.getSecondsToClick());
        component.validate();
    }
}
