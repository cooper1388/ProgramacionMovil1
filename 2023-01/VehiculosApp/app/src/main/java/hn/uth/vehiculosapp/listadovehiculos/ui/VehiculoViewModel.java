package hn.uth.vehiculosapp.listadovehiculos.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.vehiculosapp.database.VehiculoRepository;
import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;

public class VehiculoViewModel extends AndroidViewModel {

    private VehiculoRepository repository;
    private final LiveData<List<Vehiculo>> vehiculosDataset;

    public VehiculoViewModel(@NonNull Application application) {
        super(application);
        repository = new VehiculoRepository(application);
        this.vehiculosDataset = repository.getVehiculosDataset();
    }

    public LiveData<List<Vehiculo>> getVehiculosDataset() {
        return vehiculosDataset;
    }

    public void insert(Vehiculo nuevo){
        repository.insert(nuevo);
    }

    public void update(Vehiculo actualizar){
        repository.update(actualizar);
    }
}
