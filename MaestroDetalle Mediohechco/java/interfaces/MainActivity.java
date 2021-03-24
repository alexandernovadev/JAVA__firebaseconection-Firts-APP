package com.aprendiendoando.maestrodetalle.interfaces;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aprendiendoando.maestrodetalle.R;
import com.aprendiendoando.maestrodetalle.adaptadores.AdaptadorPersonajes;
import com.aprendiendoando.maestrodetalle.entidades.PersonajeVO;
import com.aprendiendoando.maestrodetalle.fragments.DetallePersonajeFragment;
import com.aprendiendoando.maestrodetalle.fragments.ListaPersonajeFragment;

public class MainActivity extends AppCompatActivity
        implements ListaPersonajeFragment.OnFragmentInteractionListener,
            DetallePersonajeFragment.OnFragmentInteractionListener,
            IComunica{


    ListaPersonajeFragment listaFragment;
    DetallePersonajeFragment detalleFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaFragment = new ListaPersonajeFragment();


        getSupportFragmentManager().
                beginTransaction().
                add(R.id.contenedorfragments, listaFragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarPersonaje(PersonajeVO personaje) {
        detalleFragment = new DetallePersonajeFragment();


        // Con esta linea puedo enviar cosas a otra clase JAva
        // En este caso envie el objeto que recibi en el envento de Listener 
        //de ListaPersonajeFragmnet
        Bundle bundleenvio = new Bundle();
        bundleenvio.putSerializable("objeto",personaje);

        detalleFragment.setArguments(bundleenvio);


        // Cargar el Frangment del Activity

        getSupportFragmentManager()
               .beginTransaction()
               .replace(R.id.contenedorfragments, detalleFragment)
               .addToBackStack(null).commit();

        //.addToBackStack --> Es para que sirva de una manera mas optima


    }
}
