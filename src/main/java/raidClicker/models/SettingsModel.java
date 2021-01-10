package raidClicker.models;

import java.awt.*;

public final class SettingsModel {
    private Point location;
    private String timeToClick;
    private String timeToRun;
    public static final transient SettingsModel DEFAULT_SETTINGS = defaultSettings();

    private SettingsModel(Point location, String timeToClick, String timeToRun) {
        this.location = location;
        this.timeToClick = timeToClick;
        this.timeToRun = timeToRun;
    }

    private static SettingsModel defaultSettings() {
        return new SettingsModel(
                null,
                "10",
                null
        );
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getTimeToClick() {
        return timeToClick;
    }

    public void setTimeToClick(String timeToClick) {
        this.timeToClick = timeToClick;
    }

    public String getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(String timeToRun) {
        this.timeToRun = timeToRun;
    }
}
