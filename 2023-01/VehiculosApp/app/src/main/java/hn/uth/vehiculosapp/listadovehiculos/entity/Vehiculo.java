package hn.uth.vehiculosapp.listadovehiculos.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehiculo_table")
public class Vehiculo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Integer idVehiculo;
    @NonNull
    @ColumnInfo(name = "marca")
    private String marca;
    @NonNull
    @ColumnInfo(name = "modelo")
    private String modelo;
    @ColumnInfo(name = "fabricacion")
    private int anio;

    public Vehiculo(@NonNull String marca, @NonNull String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    @NonNull
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(@NonNull Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
