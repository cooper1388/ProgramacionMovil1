package hn.uth.vehiculosapp.listadovehiculos.ui;

import static android.app.Activity.RESULT_OK;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import hn.uth.vehiculosapp.OnItemClickListener;
import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;
import hn.uth.vehiculosapp.listadovehiculos.ui.adapter.VehiculosAdapter;
import hn.uth.vehiculosapp.VehiculosApp;
import hn.uth.vehiculosapp.crearvehiculos.ui.CrearVehiculoActivity;
import hn.uth.vehiculosapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment implements OnItemClickListener<Vehiculo> {

    private FragmentFirstBinding binding;

    private VehiculosAdapter adaptador;

    private VehiculosApp app;
    private VehiculoViewModel vehiculosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        app = VehiculosApp.getInstance();
        vehiculosViewModel = new ViewModelProvider(this).get(VehiculoViewModel.class);

        /*app.getDataset().add(new Vehiculo("Nissan", "Sentra", 2020));
        app.getDataset().add(new Vehiculo("Honda", "Civic", 2015));
        app.getDataset().add(new Vehiculo("Ford", "Escape", 2012));
        app.getDataset().add(new Vehiculo("Mini", "Bloom", 2016));
        app.getDataset().add(new Vehiculo("BMW", "CX-7", 2019));
        app.getDataset().add(new Vehiculo("Tesla", "Model M", 2023));*/

        adaptador = new VehiculosAdapter(new ArrayList<>(), this);

        //CONSULTA A BASE DE DATOS MEDIANTE BACKGRUOND THREAD
        vehiculosViewModel.getVehiculosDataset().observe(this, vehiculos -> {
            adaptador.setItems(vehiculos);
            validarDataset();
        });

        setupReciclerView();

        return binding.getRoot();
    }

    private void validarDataset() {
        if(adaptador.getItemCount() == 0){
            binding.tvWarning.setVisibility(View.VISIBLE);
            binding.ivWarning.setVisibility(View.VISIBLE);
            binding.rvVehiculos.setVisibility(View.INVISIBLE);
        }else{
            binding.tvWarning.setVisibility(View.INVISIBLE);
            binding.ivWarning.setVisibility(View.INVISIBLE);
            binding.rvVehiculos.setVisibility(View.VISIBLE);
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupReciclerView(){
        LinearLayoutManager layoutLineal = new LinearLayoutManager(this.getContext());
        binding.rvVehiculos.setLayoutManager(layoutLineal);
        binding.rvVehiculos.setAdapter(adaptador);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Vehiculo data, int type) {
        Intent intent = new Intent(this.getContext(), CrearVehiculoActivity.class);
        intent.putExtra("VEHICULO_MARCA", data.getMarca());
        intent.putExtra("VEHICULO_MODELO", data.getModelo());
        intent.putExtra("VEHICULO_ANIO", data.getAnio());
        startActivityForResult(intent, 6, ActivityOptions.makeSceneTransitionAnimation(this.getActivity()).toBundle());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 6){
            //EDICIÓN DE UN VEHICULO EXISTENTE
            if(resultCode == RESULT_OK){
                String marca = data.getStringExtra("MARCA");//EL MODELO NUEVO
                String modelo = data.getStringExtra("MODELO");
                String anio = data.getStringExtra("ANIO");

                String marcaOriginal = data.getStringExtra("MARCA_O");//EL MODELO ORIGNAL
                String modeloOriginal = data.getStringExtra("MODELO_O");
                String anioOriginal = data.getStringExtra("ANIO_O");

                int anioInt = Integer.parseInt(anioOriginal);

                /*for(int i = 0; i<app.getDataset().size(); i++){
                    if(app.getDataset().get(i).getAnio() == anioInt && app.getDataset().get(i).getMarca().equalsIgnoreCase(marcaOriginal) && app.getDataset().get(i).getModelo().equalsIgnoreCase(modeloOriginal)){
                        app.getDataset().get(i).setModelo(modelo);
                        app.getDataset().get(i).setMarca(marca);
                        app.getDataset().get(i).setAnio(Integer.parseInt(anio));
                        break;
                    }
                }*/

                //adaptador.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}