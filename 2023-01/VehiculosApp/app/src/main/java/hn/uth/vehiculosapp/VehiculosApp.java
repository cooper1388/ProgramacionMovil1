package hn.uth.vehiculosapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;

public class VehiculosApp extends Application {

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

}
