package hn.bytecode.drycleanmanager.services;
import java.util.List;

import hn.bytecode.drycleanmanager.entity.dto.Producto;
import hn.bytecode.drycleanmanager.entity.events.ProductosEvent;
import hn.bytecode.drycleanmanager.entity.response.ProductosResponse;
import hn.bytecode.drycleanmanager.services.base.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseRepositoryImpl  {
    private static DatabaseRepositoryImpl instance;
    private DatabaseClient client;
    private EventBus eventBus;
    private double montoPago;

    private DatabaseRepositoryImpl(EventBus eventBus, String url, Long timeout) {
        client = new DatabaseClient(url, timeout);
        this.eventBus = eventBus;
        montoPago = 0.0;
    }

    //IMPLEMENTANDO PATRON DE PROGRAMACION SINGLETON
    public static DatabaseRepositoryImpl getInstance(EventBus eventBus, String url, Long timeout) {
        if (instance == null) {
            synchronized (DatabaseRepositoryImpl.class) {
                if (instance == null) {
                    instance = new DatabaseRepositoryImpl(eventBus, url, timeout);
                }
            }
        }
        return instance;
    }

    public void getProducts() {
        Call<ProductosResponse> call = client.getDatabaseService().obtenerProductos();
        call.enqueue(handleProductsResponse);
    }

    Callback<ProductosResponse> handleProductsResponse = new Callback<ProductosResponse>() {
        @Override
        public void onResponse(Call<ProductosResponse> call, Response<ProductosResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                postProducts(response.body().getItems(), null);
            } else {
                postProducts(null, response.message());
            }
        }

        @Override
        public void onFailure(Call<ProductosResponse> call, Throwable t) {
            postProducts(null, t.getMessage());
        }
    };

    private void postProducts(List<Producto> productos, String error){
        ProductosEvent event = new ProductosEvent();
        event.setData(productos);
        event.setError(error);
        eventBus.post(event);
    }
}
