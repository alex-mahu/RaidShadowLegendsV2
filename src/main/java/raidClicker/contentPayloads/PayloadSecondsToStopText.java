package raidClicker.contentPayloads;

public final class PayloadSecondsToStopText implements IPayloadInformation {
    private final String labelText;

    public PayloadSecondsToStopText(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelText() {
        return labelText;
    }
}
