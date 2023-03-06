package hn.uth.vehiculosapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hn.uth.vehiculosapp.VehiculosApp;
import hn.uth.vehiculosapp.listadovehiculos.entity.Vehiculo;
import hn.uth.vehiculosapp.listadovehiculos.entity.VehiculoDao;

@Database(entities = {Vehiculo.class}, version = 1, exportSchema = false)
public abstract class VehiculosDatabase extends RoomDatabase {
    public abstract VehiculoDao vehiculoDao();

    private static volatile VehiculosDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //GENERADOR DE INSTANCIA USANDO PATRÓN SINGLETON
    static VehiculosDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VehiculosDatabase.class){
                if(INSTANCE == null){
                    RoomDatabase.Callback miCallback = new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            databaseWriteExecutor.execute(() -> {
                                VehiculoDao dao = INSTANCE.vehiculoDao();
                                dao.deleteAll();

                                Vehiculo nuevo = new Vehiculo("Tesla", "CM", 2020);
                                dao.insert(nuevo);
                            });

                        }
                    };
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VehiculosDatabase.class, "vehiculos_database").addCallback(miCallback).build();

                }
            }
        }
        return INSTANCE;
    }


}
