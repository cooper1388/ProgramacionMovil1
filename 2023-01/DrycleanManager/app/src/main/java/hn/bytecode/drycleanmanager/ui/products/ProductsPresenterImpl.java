package hn.bytecode.drycleanmanager.ui.products;

import hn.bytecode.drycleanmanager.services.DatabaseRepositoryImpl;

public class ProductsPresenterImpl implements ProductsPresenter {
    private DatabaseRepositoryImpl repository;

    public ProductsPresenterImpl(DatabaseRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void cargarProductos() {
        this.repository.getProducts();
    }

    @Override
    public void buscarProductos(String nombre) {

    }
}
