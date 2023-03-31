package hn.bytecode.drycleanmanager.services.base;

import java.util.ArrayList;
import java.util.List;

import hn.bytecode.drycleanmanager.entity.dto.Producto;

/**
 * Clase base para eventos de eventbus.
 * Created by Cooper on 17/8/2017.
 */
public abstract class EventBase<T> {
    protected List<T> data;
    protected String error;
    protected boolean isError;

    public EventBase() {
        this.data = new ArrayList<>();
        this.error = null;
        this.isError = false;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isError() {
        return isError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        if(this.error != null && !"".equals(this.error)){
            this.isError = true;
        }else{
            this.isError = false;
        }
    }
}
