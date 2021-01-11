package raidClicker.contentPayloads.handlers;

import raidClicker.components.SettingsWrapperComponent;
import raidClicker.contentPayloads.PayloadUpdateSettings;

import static raidClicker.helpers.SettingsHelper.writeSettingsToFile;

public final class CopySettingsToStaticObject extends ComponentHandler<SettingsWrapperComponent, PayloadUpdateSettings> {

    public CopySettingsToStaticObject(SettingsWrapperComponent component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadUpdateSettings payload) {
        component.updateSettings();
        writeSettingsToFile();
    }
}
