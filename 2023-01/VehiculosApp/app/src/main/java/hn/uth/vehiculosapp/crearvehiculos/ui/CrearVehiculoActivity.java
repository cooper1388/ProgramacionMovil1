package hn.uth.vehiculosapp.crearvehiculos.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import hn.uth.vehiculosapp.R;
import hn.uth.vehiculosapp.databinding.ActivityCrearVehiculoBinding;

public class CrearVehiculoActivity extends AppCompatActivity {

    private ActivityCrearVehiculoBinding binding;
    private String marcaOriginal;
    private String modeloOriginal;
    private String anioOriginal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCrearVehiculoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGuardar.setOnClickListener(v-> {
            Intent resultado = new Intent();
            resultado.putExtra("MARCA",binding.tilMarca.getEditText().getText().toString());
            resultado.putExtra("MODELO",binding.tilModelo.getEditText().getText().toString());
            resultado.putExtra("ANIO",binding.tilAnio.getEditText().getText().toString());

            resultado.putExtra("MARCA_O", marcaOriginal);
            resultado.putExtra("MODELO_O", modeloOriginal);
            resultado.putExtra("ANIO_O", anioOriginal);
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
            marcaOriginal = marca+"";
            modeloOriginal = modelo+"";
            anioOriginal = anio+"";

            binding.tilMarca.getEditText().setText(marca);
            binding.tilModelo.getEditText().setText(modelo);
            binding.tilAnio.getEditText().setText(String.valueOf(anio));
            binding.btnGuardar.setText(getString(R.string.btn_guardar_vehiculo));
        }else{
            binding.btnGuardar.setText(getString(R.string.btn_crear_vehiculo));
        }
    }
}