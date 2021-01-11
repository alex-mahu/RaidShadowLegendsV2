package raidClicker.contentPayloads;

public final class PayloadSecondsToClickLabel implements IPayloadInformation {
    private final String labelText;

    public PayloadSecondsToClickLabel(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelText() {
        return labelText;
    }
}
