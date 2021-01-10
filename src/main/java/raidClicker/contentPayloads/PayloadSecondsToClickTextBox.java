package raidClicker.contentPayloads;

public final class PayloadSecondsToClickTextBox implements IPayloadInformation {
    private final String secondsToClick;

    public PayloadSecondsToClickTextBox(String secondsToClick) {
        this.secondsToClick = secondsToClick;
    }

    public String getSecondsToClick() {
        return secondsToClick;
    }
}
