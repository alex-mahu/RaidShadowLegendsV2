package raidClicker.contentPayloads.handlers;

import raidClicker.components.LoadSaveSettingsComponent;
import raidClicker.contentPayloads.*;

import java.awt.*;

import static java.util.Objects.isNull;
import static raidClicker.helpers.SettingsHelper.SETTINGS;

public final class SettingsToUiChanger extends ComponentHandler<LoadSaveSettingsComponent, PayloadUpdateUiSettings> {

    public SettingsToUiChanger(LoadSaveSettingsComponent component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadUpdateUiSettings payload) {
        setLocation(SETTINGS.getLocation());
        setTimeToClick(SETTINGS.getTimeToClick());
        setTimeToRun(SETTINGS.getTimeToRun());
    }

    private void setTimeToRun(String timeToRun) {
        if (isNull(timeToRun)) {
            return;
        }
        ComponentManager.addPayloadToConsume(new PayloadSecondsToRunTextBox(timeToRun));
    }

    private void setTimeToClick(String timeToClick) {
        if (isNull(timeToClick)) {
            return;
        }
        ComponentManager.addPayloadToConsume(new PayloadSecondsToClickTextBox(timeToClick));
    }

    private void setLocation(Point location) {
        if (isNull(location)) {
            return;
        }
        ComponentManager.addPayloadToConsume(new PayloadLocation(location));
    }
}
