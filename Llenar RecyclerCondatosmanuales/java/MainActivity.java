package com.aprendiendoando.firebaseimageslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerviewmain;
    ArrayList<Datos_modelo> arrayListdatosmodelo;
    AdaptadorDatos adaptadordatosme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerviewmain = (RecyclerView) findViewById(R.id.recyclerview_main);
        recyclerviewmain.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        arrayListdatosmodelo = new ArrayList<>();
        llenarDatos();


        adaptadordatosme = new AdaptadorDatos(arrayListdatosmodelo);

        recyclerviewmain.setAdapter(adaptadordatosme);
    }

    private void llenarDatos() {
        arrayListdatosmodelo.add(new Datos_modelo("Pepe","Arevalo"));
        arrayListdatosmodelo.add(new Datos_modelo("Juan","Salgado"));
        arrayListdatosmodelo.add(new Datos_modelo("Caroline","Sierra"));
        arrayListdatosmodelo.add(new Datos_modelo("Sara","Nova"));
        arrayListdatosmodelo.add(new Datos_modelo("Pepe","Arevalo"));
        arrayListdatosmodelo.add(new Datos_modelo("Juan","Salgado"));
        arrayListdatosmodelo.add(new Datos_modelo("Caroline","Sierra"));
        arrayListdatosmodelo.add(new Datos_modelo("Sara","Nova"));
        arrayListdatosmodelo.add(new Datos_modelo("Pepe","Arevalo"));
        arrayListdatosmodelo.add(new Datos_modelo("Juan","Salgado"));
        arrayListdatosmodelo.add(new Datos_modelo("Caroline","Sierra"));
        arrayListdatosmodelo.add(new Datos_modelo("Sara","Nova"));
    }
}
