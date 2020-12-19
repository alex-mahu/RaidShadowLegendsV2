package raidClicker.contentPayloads.handlers;

import raidClicker.contentPayloads.IPayloadInformation;

import javax.swing.*;

public abstract class ComponentHandler<T extends JComponent, R extends IPayloadInformation> {
    protected T component;

    public ComponentHandler(T component) {
        this.component = component;
    }

    public abstract void consumePayload(R payload);

}
