package hn.bytecode.drycleanmanager.services.base;

/**
 * Event Bus Class.
 * Created by Joseph Cooper on 8/6/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
