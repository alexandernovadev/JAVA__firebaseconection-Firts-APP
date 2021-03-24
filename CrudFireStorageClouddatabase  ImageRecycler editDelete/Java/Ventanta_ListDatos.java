package com.aprendiendoando.subirimagenesfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Ventanta_ListDatos extends AppCompatActivity implements View.OnClickListener {


    FirebaseFirestore basedatos = FirebaseFirestore.getInstance();
    public ArrayList<ModeloDatos> arraydatos;
    public AdaptaDatos adaptador_claseme;
    private RecyclerView recyvclerview;

    private ImageView imabtn;

    CollectionReference coleccionref = basedatos.collection("Datos");
    //ESTA LINEA DE ARRIBA ESTA OBSOLETA

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventanta__list_datos);



        recyvclerview = (RecyclerView) findViewById(R.id.recycler_datos);
        recyvclerview.setLayoutManager(new LinearLayoutManager(this));

        //datostv = (TextView) findViewById(R.id.text_view_data);

        imabtn = (ImageView) findViewById(R.id.imagearrow);


        imabtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(Ventanta_ListDatos.this ,MainActivity.class);
                startActivity(intent);
            }

        });

        llenado();


    }

    public void llenado()
    {
        arraydatos = new ArrayList<>();

        coleccionref.addSnapshotListener(this, new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e)
            {
                if (e != null)
                {
                    return;
                }


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {

                    ModeloDatos note = documentSnapshot.toObject(ModeloDatos.class);
                    arraydatos.add(note);

                }


                //Tocaba pasarle el parametro de Context aqui
                adaptador_claseme = new AdaptaDatos(arraydatos,getApplicationContext());


                adaptador_claseme.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {

                        Toast.makeText(Ventanta_ListDatos.this,
                                arraydatos.get(recyvclerview.getChildAdapterPosition(v)).getNombre()
                                , Toast.LENGTH_SHORT).show();



                        Intent intent=new Intent(Ventanta_ListDatos.this,
                                DetalleDato.class);


                        // Para enviar un objeto hay que implementar Serializable en el modelo
                        // Despues hay que crear un Bundle
                        // a ese bundle se le asigna .putSerializable("Nombre ref" , objeto)

                        // Creee una intancia del modelo llamado item
                        // y le asigne el objeto que se selecciono


                        ModeloDatos item = arraydatos.get(recyvclerview.getChildAdapterPosition(v));

                        //Log.d("DATOS" , n+a+u);

                        Bundle bundle=new Bundle();
                        bundle.putSerializable("Item",item);

                        // Al intent se le asigna putExtras y se le pasa en bundle
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                recyvclerview.setAdapter(adaptador_claseme);

            }

        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
