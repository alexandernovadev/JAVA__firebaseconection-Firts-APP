package c.aprendiendoando.pasarventanas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnSig1(View view) {

        // Esta es la linea de codigo que pasa de una actividad a otra
        // los parametros que hay que pasar son dos, la ventana en la
        // que estoy y el otro es la ventana a la que voy
        // Despues le digo que se inicie con startActivity()

        Intent miintent = new Intent(this, ventanados.class);
        startActivity(miintent);

    }

}
