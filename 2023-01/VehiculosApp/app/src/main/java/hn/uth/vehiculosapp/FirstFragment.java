package hn.uth.vehiculosapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import hn.uth.vehiculosapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment implements OnItemClickListener<Vehiculo>{

    private FragmentFirstBinding binding;

    private VehiculosAdapter adaptador;

    private VehiculosApp app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        app = VehiculosApp.getInstance();

        /*app.getDataset().add(new Vehiculo("Nissan", "Sentra", 2020));
        app.getDataset().add(new Vehiculo("Honda", "Civic", 2015));
        app.getDataset().add(new Vehiculo("Ford", "Escape", 2012));
        app.getDataset().add(new Vehiculo("Mini", "Bloom", 2016));
        app.getDataset().add(new Vehiculo("BMW", "CX-7", 2019));
        app.getDataset().add(new Vehiculo("Tesla", "Model M", 2023));*/

        validarDataset();

        adaptador = new VehiculosAdapter(app.getDataset(), this);

        setupReciclerView();

        return binding.getRoot();
    }

    private void validarDataset() {
        if(app.getDataset().size() == 0){
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
        if(this.adaptador != null && app != null && this.adaptador.getItemCount() <= app.getDataset().size()){
            this.adaptador.setItems(app.getDataset());
            validarDataset();
        }
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
}