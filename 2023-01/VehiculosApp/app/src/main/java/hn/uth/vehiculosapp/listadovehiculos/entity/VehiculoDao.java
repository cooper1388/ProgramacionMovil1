package hn.uth.vehiculosapp.listadovehiculos.entity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VehiculoDao {

    @Insert
    void insert(Vehiculo nuevo);

    @Update
    void update(Vehiculo carro);

    @Query("DELETE FROM vehiculo_table")
    void deleteAll();
    @Delete
    void delete(Vehiculo eliminar);

    @Query("select * from vehiculo_table order by modelo asc")
    LiveData<List<Vehiculo>> getVehiculos();
}
