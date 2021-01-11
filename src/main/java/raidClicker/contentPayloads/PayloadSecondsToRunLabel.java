package raidClicker.contentPayloads;

public final class PayloadSecondsToRunLabel implements IPayloadInformation {
    private final String labelText;

    public PayloadSecondsToRunLabel(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelText() {
        return labelText;
    }
}
