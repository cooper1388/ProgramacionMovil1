package hn.bytecode.drycleanmanager.services.base;

/**
 * Clase PresenterBase
 * Created by Joseph Cooper on 12/3/2018.
 */
public interface PresenterBase<T> {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void onEventMainThread(T event);
}
