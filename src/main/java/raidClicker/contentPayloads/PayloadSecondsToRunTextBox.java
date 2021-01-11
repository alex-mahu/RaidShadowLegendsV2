package raidClicker.contentPayloads;

public final class PayloadSecondsToRunTextBox implements IPayloadInformation {
    private final String secondsToRun;

    public PayloadSecondsToRunTextBox(String secondsToRun) {
        this.secondsToRun = secondsToRun;
    }

    public String getSecondsToRun() {
        return secondsToRun;
    }
}
