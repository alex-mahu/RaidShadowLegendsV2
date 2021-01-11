package raidClicker.contentPayloads.handlers;

import raidClicker.components.LocationComponent;
import raidClicker.contentPayloads.PayloadLocation;

public final class LocationChanger extends ComponentHandler<LocationComponent, PayloadLocation> {

    public LocationChanger(LocationComponent component) {
        super(component);
    }

    @Override
    public void consumePayload(PayloadLocation payload) {
        component.setMouseLocation(payload.getLocation());
        component.validate();
    }
}
