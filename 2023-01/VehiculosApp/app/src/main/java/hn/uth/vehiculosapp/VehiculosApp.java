package hn.uth.vehiculosapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class VehiculosApp extends Application {

    private List<Vehiculo> dataset;

    private static VehiculosApp instance;

    public static VehiculosApp getInstance(){
        if(instance == null){
            synchronized (VehiculosApp.class){
                if(instance == null){
                    instance = new VehiculosApp();
                }
            }
        }
        return instance;
    }

    public List<Vehiculo> getDataset(){
        if(this.dataset == null){
            this.dataset = new ArrayList<>();
        }
        return this.dataset;
    }
}
