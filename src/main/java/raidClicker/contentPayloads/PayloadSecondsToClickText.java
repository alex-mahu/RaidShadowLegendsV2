package raidClicker.contentPayloads;

public final class PayloadSecondsToClickText implements IPayloadInformation {
    private final String labelText;

    public PayloadSecondsToClickText(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelText() {
        return labelText;
    }
}
