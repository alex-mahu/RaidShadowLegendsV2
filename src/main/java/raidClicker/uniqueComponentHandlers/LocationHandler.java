package raidClicker.uniqueComponentHandlers;

import raidClicker.components.LocationComponent;

import java.awt.*;

public final class LocationHandler {

    static LocationComponent locationComponent;

    private LocationHandler() {
    }

    public static void loadComponent(LocationComponent component) {
        locationComponent = component;
    }

    public static Point getLocation() {
        return locationComponent.getSavedMouseLocation();
    }

    public static void setLocation(Point mouseLocation) {
        locationComponent.setMouseLocation(mouseLocation);
    }

}
