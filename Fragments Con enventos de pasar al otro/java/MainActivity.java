package com.aprendiendoando.fragments;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


// Aca en la clase, hay que implementar los fragamentos que cree
// Cristian dijo que despues de API 16 no se utiliza, pero me sale error y yo
// estoy utilizando API 23 ?? WTF

public class MainActivity extends AppCompatActivity
implements FragmentVerde.OnFragmentInteractionListener ,FragmentRojo.OnFragmentInteractionListener,
    FragmentAzul.OnFragmentInteractionListener{


    // Se crean los fragmentos como si fueran elementos de la Vista,
    // Es que lo son jajaja
    FragmentRojo fragmentRojo;
    FragmentVerde fragmentVerde;
    FragmentAzul fragmentAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se instancian
        fragmentAzul = new FragmentAzul();
        fragmentVerde = new FragmentVerde();
        fragmentRojo = new FragmentRojo();


        // Se asigna una vista por defecto al FragmaentLayout
        // Este fragment Layout tiene todas las vistas

        // add tiene dos parametros, el contenedor de los fragmaents y el contenedor por defecto
        // que en este caso seria el Rojo

        getSupportFragmentManager().beginTransaction()
        .add(R.id.ContenedorFragments,fragmentRojo).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void OnClick(View view) {

        // Esta variable asigna las vistas dependiendo el Click del usuario

        // En los botones hay que agregar replace por .add ya creo uno por defecto
        // Entonces se utilza el metodo .replace

        FragmentTransaction transicion = getSupportFragmentManager().beginTransaction();

        switch (view.getId())
        {
            case R.id.btnRojo:

                transicion.replace(R.id.ContenedorFragments, FragmentRojo);

                break;

            case R.id.btnVerde:

                transicion.replace(R.id.ContenedorFragments, fragmentVerde);

                break;

            case R.id.btnAzul:

                transicion.replace(R.id.ContenedorFragments, fragmentAzul);

                break;
        }

        transicion.commit();
    }
}
