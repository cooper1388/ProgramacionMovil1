package hn.uth.vehiculosapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hn.uth.vehiculosapp.databinding.VehiculoItemBinding;

public class VehiculosAdapter extends RecyclerView.Adapter<VehiculosAdapter.ViewHolder> {

    private List<Vehiculo> dataset;
    private OnItemClickListener<Vehiculo> manejadorEventoClick;

    public VehiculosAdapter(List<Vehiculo> dataset, OnItemClickListener<Vehiculo> manejadorEventoClick) {
        copiarDataset(dataset);
        this.manejadorEventoClick = manejadorEventoClick;
    }

    @NonNull
    @Override
    public VehiculosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VehiculoItemBinding binding = VehiculoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VehiculosAdapter.ViewHolder holder, int position) {
        Vehiculo vehiculoMostrar = dataset.get(position);
        holder.binding.tvMarca.setText(vehiculoMostrar.getMarca());
        holder.binding.tvModelo.setText(vehiculoMostrar.getModelo());
        holder.binding.tvAnio.setText(String.valueOf(vehiculoMostrar.getAnio()));
        holder.setOnClickListener(vehiculoMostrar, manejadorEventoClick);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Vehiculo> datasetNuevo){
        copiarDataset(datasetNuevo);
        //this.dataset.addAll(datasetNuevo);
        notifyDataSetChanged();
    }

    public void copiarDataset(List<Vehiculo> datasetNuevo){
        if(this.dataset == null){
            this.dataset = new ArrayList<>();
        }
        this.dataset.clear();
        datasetNuevo.forEach( d -> {
            this.dataset.add(d);
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        VehiculoItemBinding binding;

        public ViewHolder(VehiculoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setOnClickListener(final Vehiculo vehiculoSeleccionado, final OnItemClickListener<Vehiculo> listner){
            this.binding.cardVehiculo.setOnClickListener(v-> listner.onItemClick(vehiculoSeleccionado, 1));
        }
    }
}
