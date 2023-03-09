package hn.uth.vehiculosapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;
import hn.uth.vehiculosapp.listadovehiculos.entity.VehiculoDao;

public class VehiculoRepository {
    private VehiculoDao vehiculoDao;
    private LiveData<List<Vehiculo>> vehiculosDataset;

    public VehiculoRepository(Application app){
        VehiculosDatabase db = VehiculosDatabase.getDatabase(app);
        vehiculoDao = db.vehiculoDao();
        vehiculosDataset = vehiculoDao.getVehiculos();
    }

    public LiveData<List<Vehiculo>> getVehiculosDataset() {
        return vehiculosDataset;
    }

    public void insert(Vehiculo nuevo){
        VehiculosDatabase.databaseWriteExecutor.execute(() ->{
            vehiculoDao.insert(nuevo);
        });
    }

    public void update(Vehiculo actualizar){
        VehiculosDatabase.databaseWriteExecutor.execute(() ->{
            vehiculoDao.update(actualizar);
        });
    }

    public void delete(Vehiculo eliminar){
        VehiculosDatabase.databaseWriteExecutor.execute(() ->{
            vehiculoDao.delete(eliminar);
        });
    }

    public void deleteAll(){
        VehiculosDatabase.databaseWriteExecutor.execute(() ->{
            vehiculoDao.deleteAll();
        });
    }
}
