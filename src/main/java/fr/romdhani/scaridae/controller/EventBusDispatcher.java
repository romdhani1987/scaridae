package fr.romdhani.scaridae.controller;

import com.google.common.eventbus.EventBus;

public class EventBusDispatcher {
    private final EventBus eventBus = new EventBus();

    private EventBusDispatcher() {
    }

    private static class InstanceHolder {
        static final EventBusDispatcher INSTANCE = new EventBusDispatcher();
    }

    public static EventBusDispatcher getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public <T> void register(T event) {
        eventBus.register(event);
    }

    public <T> void unRegister(T event) {
        eventBus.unregister(event);
    }
}
