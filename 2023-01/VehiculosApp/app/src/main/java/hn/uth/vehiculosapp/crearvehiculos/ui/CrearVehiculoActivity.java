package hn.uth.vehiculosapp.crearvehiculos.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

import hn.uth.vehiculosapp.R;
import hn.uth.vehiculosapp.databinding.ActivityCrearVehiculoBinding;
import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;
import hn.uth.vehiculosapp.listadovehiculos.ui.VehiculoViewModel;

public class CrearVehiculoActivity extends AppCompatActivity {

    private ActivityCrearVehiculoBinding binding;
    private int idVehiculo;

    private VehiculoViewModel vehiculosViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vehiculosViewModel = new ViewModelProvider(this).get(VehiculoViewModel.class);

        binding = ActivityCrearVehiculoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


        binding.btnGuardar.setOnClickListener(v-> {
            Intent resultado = new Intent();
            resultado.putExtra("ID", idVehiculo);
            resultado.putExtra("MARCA",binding.tilMarca.getEditText().getText().toString());
            resultado.putExtra("MODELO",binding.tilModelo.getEditText().getText().toString());
            resultado.putExtra("ANIO",binding.tilAnio.getEditText().getText().toString());

            setResult(Activity.RESULT_OK, resultado);
            finish();
        });

        obtenerDatosIntent(getIntent());
    }

    private void obtenerDatosIntent(Intent intent) {
        String marca = intent.getStringExtra("VEHICULO_MARCA");

        if(marca != null){
            String modelo = intent.getStringExtra("VEHICULO_MODELO");
            int anio = intent.getIntExtra("VEHICULO_ANIO", 0);
            idVehiculo = intent.getIntExtra("VEHICULO_ID", 0);

            binding.tilMarca.getEditText().setText(marca);
            binding.tilModelo.getEditText().setText(modelo);
            binding.tilAnio.getEditText().setText(String.valueOf(anio));
            binding.btnGuardar.setText(getString(R.string.btn_guardar_vehiculo));

            binding.toolbar.setTitle(R.string.title_update_vehicle);
        }else{
            binding.btnGuardar.setText(getString(R.string.btn_crear_vehiculo));

            binding.toolbar.setTitle(R.string.title_create_vehicle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ME SIRVE PARA INFLAR EL MENÚ (ASOCIAR EL MENÚ CREADO AL ACTIVITY O TOOLBAR DONDE SE VA A MOSTRAR)
        getMenuInflater().inflate(R.menu.eliminar_vehiculo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //SIRVE PARA INDICAR QUE HACER AL PRESIONAR LA OPCIÓN DEL MENÚ

        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();
        }else if(id == R.id.action_delete_vehicle){
            Vehiculo vehiculoEliminar = new Vehiculo(binding.tilMarca.getEditText().getText().toString(),
                    binding.tilModelo.getEditText().getText().toString(),
                    0);
            vehiculoEliminar.setIdVehiculo(idVehiculo);

            vehiculosViewModel.delete(vehiculoEliminar);

            Snackbar.make(binding.clCrearVehiculo, getString(R.string.mensaje_eliminacion_vehiculo), Snackbar.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}