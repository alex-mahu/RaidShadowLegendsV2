package raidClicker.contentPayloads;

import raidClicker.contentPayloads.handlers.ComponentHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ComponentManager {

    private final static List<IPayloadInformation> payloads = new ArrayList<>();
    private static HashMap<Class<? extends IPayloadInformation>, ComponentHandler> payloadToHandler = new HashMap<>();

    private ComponentManager() {
    }

    public static void registerComponentHandler(Class<? extends IPayloadInformation> payloadClass, ComponentHandler handler) {
        payloadToHandler.put(payloadClass, handler);
    }

    public static void addPayloadToConsume(IPayloadInformation payload) {
        synchronized (payloads) {
            payloads.add(payload);
        }
    }

    private static void consumePayload(IPayloadInformation payload) {
        payloadToHandler.get(payload.getClass()).consumePayload(payload);
    }

    public static void checkAndConsumePayloads() {
        synchronized (payloads) {
            if (payloads.isEmpty()) {
                return;
            }

            IPayloadInformation payload = payloads.get(0);
            consumePayload(payload);
            payloads.remove(payload);
        }
    }
}
