package hn.uth.vehiculosapp.listadovehiculos.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.Explode;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import hn.uth.vehiculosapp.R;
import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;
import hn.uth.vehiculosapp.VehiculosApp;
import hn.uth.vehiculosapp.crearvehiculos.ui.CrearVehiculoActivity;
import hn.uth.vehiculosapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private VehiculosApp app;

    private String marca;
    private String modelo;
    private String anio;

    private VehiculoViewModel vehiculosViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        app = VehiculosApp.getInstance();
        vehiculosViewModel = new ViewModelProvider(this).get(VehiculoViewModel.class);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CrearVehiculoActivity.class);
            startActivityForResult(intent, 7, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        });
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 7){
            //ES LA CREACIÓN DE UN VEHICULO
            if(resultCode == RESULT_OK){
                marca = data.getStringExtra("MARCA");
                modelo = data.getStringExtra("MODELO");
                anio = data.getStringExtra("ANIO");

                vehiculosViewModel.insert(new Vehiculo(marca, modelo, Integer.parseInt(anio)));
               // app.getDataset().add();

                String datosVehiculo = marca + " "+modelo + " ("+anio+")";

                Snackbar.make(binding.clMainLayout, getString(R.string.mensaje_creacion_vehiculo, datosVehiculo), Snackbar.LENGTH_LONG).show();
            }
        }else if(requestCode == 6){
            //EDICIÓN DE UN VEHICULO EXISTENTE
            if(resultCode == RESULT_OK){
                marca = data.getStringExtra("MARCA");//EL MODELO NUEVO
                modelo = data.getStringExtra("MODELO");
                anio = data.getStringExtra("ANIO");

                String marcaOriginal = data.getStringExtra("MARCA_O");//EL MODELO ORIGNAL
                String modeloOriginal = data.getStringExtra("MODELO_O");
                String anioOriginal = data.getStringExtra("ANIO_O");

                int anioInt = Integer.parseInt(anioOriginal);

                /*for(int i = 0; i<app.getDataset().size(); i++){
                    if(app.getDataset().get(i).getAnio() == anioInt && app.getDataset().get(i).getMarca().equalsIgnoreCase(marcaOriginal) && app.getDataset().get(i).getModelo().equalsIgnoreCase(modeloOriginal)){
                        app.getDataset().get(i).setModelo(modelo);
                        app.getDataset().get(i).setMarca(marca);
                        app.getDataset().get(i).setAnio(anioInt);
                        break;
                    }
                }*/

                String datosVehiculo = marca + " "+modelo + " ("+anio+")";

                Snackbar.make(binding.clMainLayout, getString(R.string.mensaje_mod_vehiculo, datosVehiculo), Snackbar.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}