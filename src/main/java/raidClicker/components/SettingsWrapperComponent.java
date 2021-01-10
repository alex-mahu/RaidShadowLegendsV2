package raidClicker.components;

import javax.swing.*;


import static com.google.common.base.Strings.isNullOrEmpty;
import static raidClicker.helpers.SettingsHelper.SETTINGS;

public final class SettingsWrapperComponent extends JComponent {
    private final SecondsToWaitForClick secondsToWaitForClick;
    private final SecondsToRun secondsToRun;
    private final LocationComponent locationComponent;

    public SettingsWrapperComponent(SecondsToWaitForClick secondsToWaitForClick, SecondsToRun secondsToRun, LocationComponent locationComponent) {
        this.secondsToWaitForClick = secondsToWaitForClick;
        this.secondsToRun = secondsToRun;
        this.locationComponent = locationComponent;
    }

    public void updateSettings() {
        SETTINGS.setLocation(locationComponent.getSavedMouseLocation());
        final String secondsToWaitForClickText = secondsToWaitForClick.getText();
        SETTINGS.setTimeToClick(isNullOrEmpty(secondsToWaitForClickText) ? null : secondsToWaitForClickText);
        final String secondsToRunText = secondsToRun.getText();
        SETTINGS.setTimeToRun(isNullOrEmpty(secondsToRunText) ? null : secondsToRunText);
    }
}
