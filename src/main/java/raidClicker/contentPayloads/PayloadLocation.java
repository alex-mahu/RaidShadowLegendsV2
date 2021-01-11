package raidClicker.contentPayloads;

import java.awt.*;

public final class PayloadLocation implements IPayloadInformation {
    private final Point location;

    public PayloadLocation(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
