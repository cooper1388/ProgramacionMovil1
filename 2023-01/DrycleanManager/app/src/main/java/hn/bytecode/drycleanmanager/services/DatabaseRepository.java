package hn.bytecode.drycleanmanager.services;

import hn.bytecode.drycleanmanager.entity.response.ProductosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {
    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/jefftoncontin/sales/products")
    Call<ProductosResponse> obtenerProductos();
/*
    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/pls/apex/ingenieria_uth/ahorro/integrantes/")
    Call<ResponseBody> crearNuevoIntegrante(@Body Integrante integrante);

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @PUT("/pls/apex/ingenieria_uth/ahorro/integrantes/")
    Call<ResponseBody> actualizarIntegrante(@Body Integrante integrante);

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ingenieria_uth/ahorro/perfiles")
    Call<PerfilesResponse> obtenerPerfiles();

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/pls/apex/ingenieria_uth/ahorro/integrante/{id}")
    Call<ResponseBody> agregarpagoIntegrante(@Path("id") int id, @Body FechaPago integrante);

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ingenieria_uth/ahorro/saldos")
    Call<PagosResponse> obtenerPagos(@Query("id") int id);

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ingenieria_uth/ahorro/balance")
    Call<Balance> obtenerBalance(@Query("year") int year);

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ingenieria_uth/ahorro/ahorros")
    Call<AhorrosResponse> obtenerDetalleAhorro();

    @Headers({
            "Content-Type: application/json",
            "Accept-Charset: utf-8",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("/pls/apex/ingenieria_uth/ahorro/semanas")
    Call<SemanasAhorroResponse> obtenerSemanasAhorro();*/
}
