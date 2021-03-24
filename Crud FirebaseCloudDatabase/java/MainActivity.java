package com.aprendiendoando.firebasecloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

// Librerias de FIREBASE
// No olvidar que en gradle hay que colocar la libreria oficiales.
// o para que sirvan en todo el proyecto
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


// AppCompatActivity:  Clase base para actividades que utilizan las funciones de la
// barra de acciones de la biblioteca de soporte .

public class MainActivity extends AppCompatActivity 
{

    EditText ed_nombre, ed_apellido;

    // Es importante crear una instancia para todas las herramientas
    // de Firebase, aqui cree una instancia para
    FirebaseFirestore basedatos = FirebaseFirestore.getInstance();
    ArrayList<ModeloDatos> arrayList_datos;
    Adaptador_datos adaptador_clasejava;
    RecyclerView recyvclerview;

    CollectionReference coleccionref = basedatos.collection("Coleccion");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_nombre   = (EditText) findViewById(R.id.ed_nombre);
        ed_apellido = (EditText) findViewById(R.id.ed_apellido);
        arrayList_datos = new ArrayList<>();
        recyvclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyvclerview.setLayoutManager(new LinearLayoutManager(this));
        llenado();
        adaptador_clasejava = new Adaptador_datos(arrayList_datos);
        recyvclerview.setAdapter(adaptador_clasejava);

        // Esta linea borra todo lo del RecyvlerView
        //Porque cuando ingreso datos se vuelve a crear muchos datos
        //Es por si estoy en la misma activity

        // Primero clear y depues la lista con arrayList_datos.add(...);

        arrayListdatosmodelo.clear();

    }



    public void llenado()
    {

        coleccionref.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }


                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    ModeloDatos note = documentSnapshot.toObject(ModeloDatos.class);
                    arrayList_datos.add(new ModeloDatos(note.getNombre(), note.getApellido()));

                }

                adaptador_clasejava = new Adaptador(arrayList_datos);
                recyvclerview.setAdapter(adaptador_clasejava);

            }

        });

        arrayList_datos.add(new ModeloDatos("Pepe","sirva"));
        arrayList_datos.add(new ModeloDatos("Juan","sirva"));
        arrayList_datos.add(new ModeloDatos("Pepeito","sirva"));


    }

    public void botonsave(View view)
    {

        String nombre   = ed_nombre.getText().toString();
        String apellido = ed_apellido.getText().toString();
        //String id = UUID.randomUUID().toString();


       Map<String, Object> notas = new HashMap<>();

       notas.put("Nombre",nombre);
       notas.put("Apellido",apellido);

        basedatos.collection("Coleccion").document("Mis Notas").set(notas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       Toast.makeText(MainActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        ModeloDatos note = new ModeloDatos(nombre, apellido);

        coleccionref.add(note);
    }
}
