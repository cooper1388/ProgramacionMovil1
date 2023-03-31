package hn.bytecode.drycleanmanager.services.base;

import java.util.List;

/**
 * Clase ViewBase
 * Created by Joseph Cooper on 12/3/2018.
 */
public interface ViewBase<T> {
    void onDataChange(List<T> data);
    void showProgress();
    void hideProgress();
    void onDataError(String error);
}
