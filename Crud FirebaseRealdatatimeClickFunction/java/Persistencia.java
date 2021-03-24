package com.aprendiendoando.firebaseandroid;

// Se trae esta libreria
import com.google.firebase.database.FirebaseDatabase;

// Se extiende con eso de androdi que no se porque jajaja sera toda la app ??
public class Persistencia extends android.app.Application{


    // Metodo onvreate que es el metodo que crea una actividad
    @Override
    public void onCreate() {
        super.onCreate();

        // Codigo de la persistencia, este pagina JAVA tiene que ser la principal
        // Asi que voy al manifest y le digo que esta pagina java es la pronciapal
        //  android:name=".Persistencia" --> eso va dentro del manifest
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
