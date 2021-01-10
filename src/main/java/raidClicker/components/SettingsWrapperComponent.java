package raidClicker.components;

import raidClicker.exceptions.LocationNotParsable;

import javax.swing.*;


import static com.google.common.base.Strings.isNullOrEmpty;
import static raidClicker.helpers.SettingsHelper.SETTINGS;
import static raidClicker.models.SettingsModel.DEFAULT_SETTINGS;

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
        try {
            SETTINGS.setLocation(locationComponent.getSavedMouseLocation());
        } catch (LocationNotParsable ex) {
            SETTINGS.setLocation(DEFAULT_SETTINGS.getLocation());
        }
        final String secondsToWaitForClickText = secondsToWaitForClick.getText();
        SETTINGS.setTimeToClick(isNullOrEmpty(secondsToWaitForClickText) ? DEFAULT_SETTINGS.getTimeToClick() : secondsToWaitForClickText);
        final String secondsToRunText = secondsToRun.getText();
        SETTINGS.setTimeToRun(isNullOrEmpty(secondsToRunText) ? DEFAULT_SETTINGS.getTimeToRun() : secondsToRunText);
    }
}
