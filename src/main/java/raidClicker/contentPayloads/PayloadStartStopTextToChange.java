package raidClicker.contentPayloads;

public final class PayloadStartStopTextToChange implements IPayloadInformation {
    private final String buttonText;

    public PayloadStartStopTextToChange(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}
