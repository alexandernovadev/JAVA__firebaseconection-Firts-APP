package com.aprendiendoando.firebaseandroid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


// Traer el modelo que creamos en Java
import com.aprendiendoando.firebaseandroid.models.persona;

// Estas son la librerias de Firebase OJO-> Hay que poner unas lineas en el gradle.app,
// estas son las lineas de implementation son las que van en gradle, mirar la documentacion de firebase

//implementation 'com.google.firebase:firebase-core:16.0.4'
//implementation 'com.google.firebase:firebase-database:16.0.3'

//implementation 'com.android.support:design:28.0.0' --> Esta es para los efectos


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    MediaPlayer sonido;
    private Vibrator vibrator;
    // Se crea una lista,y esa lista se asigna a un arrayadapter
    // Ese arrayadapter se le asigna el modelo persona
    private List<persona> listPerson = new ArrayList<persona>();
    ArrayAdapter<persona> arrayAdapterPersona;

    EditText ed_nombre,ed_apellido,ed_correo, ed_pass;
    ListView lv_presonas;

    // Del modelo persona creo una instancia
    persona personaseleccionada;


    // Aqui se asisgnan variables para llamar la base de datos de firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    // Esta es la linea de codigo que pasa de una actividad a otra
    // los parametros que hay que pasar son dos, la ventana en la
    // que estoy y el otro es la ventana a la que voy
    // Despues le digo que se inicie con startActivity()

    // Intent miintent = new Intent(this, ventanados.class);
    // startActivity(miintent);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esta linea evita que la pantalla se rote, lo feo es que toca
        // colocarlo en todos los activity.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        ed_nombre = findViewById(R.id.txt_nombrePersona);
        ed_apellido = findViewById(R.id.txt_appPersona);
        ed_correo = findViewById(R.id.txt_correoPersona);
        ed_pass = findViewById(R.id.txt_passwordPersona);

        lv_presonas = findViewById(R.id.lv_datosPersonas);

        sonido = MediaPlayer.create(this, R.raw.mario);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Lineas de firebase para abrir la base de datos

        // Para hacer funciones con firebase, toca hacerlo depues de inicializar firebase
        inicializarFirebase();
        listarDatos();

        // Al hacer click en uun item hacemos lo siguiente
        lv_presonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Lo que esta arriba son las variables que nbecesita onItemClick
                // el adpatador, la visara, la posicione del click, y el ID

                personaseleccionada = (persona) parent.getItemAtPosition(position);
                // Aqui arriba se asigna el valor del item y se guarda el persona seleccionda,
                // que es modelo
                // Aqui abajo se asigan los valores son los "getters" --> voy entendiendo como funcionan

                ed_nombre.setText(personaseleccionada.getNombre());
                ed_apellido.setText(personaseleccionada.getApellido());
                ed_correo.setText(personaseleccionada.getCorreo());
                ed_pass.setText(personaseleccionada.getPassword());
            }
        });

    }

    // Con este metodo se trae los datos
    private void listarDatos() {

        // OJOOOO este metodo addValueEventListener crea los metodos onDataChange y onCancelled
        // Ondatachenge trae un obejto llamado datasnapshot, ese objeto trae los valores
        // y se trae uno por uno con un for
        // Despues esos datos se asignar primero a la lista y despues al arrayadpater
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    persona p = objSnaptshot.getValue(persona.class);


                    //private List<persona> listPerson = new ArrayList<persona>();
                    // ArrayAdapter<persona> arrayAdapterPersona;
                    // ListView lv_presonas;

                    listPerson.add(p);
                    arrayAdapterPersona =
                            new ArrayAdapter<persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    lv_presonas.setAdapter(arrayAdapterPersona);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void inicializarFirebase() {
        // Se inicia firebsae
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Esta es una manera incorrecta de persistencia
        // Para hacerla bien se crea una archovo java con la persistencia, yo la llame asi "persistencia"
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    // Este metodo crear el menu, los iconos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Aqui van los eventos del menu, los itmes
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        String nombre = ed_nombre.getText().toString();
        String correo = ed_correo.getText().toString();
        String password = ed_pass.getText().toString();
        String app = ed_apellido.getText().toString();


        switch (item.getItemId()){

            case R.id.icon_add:
            {
                Toast.makeText(this,"Add",Toast.LENGTH_LONG).show();

                if (nombre.equals("")||correo.equals("")||password.equals("")||app.equals("")){
                    validacion();
                }
                else {
                    // Esto es muy importante porque abre una instancia del modelo persona
                    // y pues a ese moledo se le asignan los valores de los campos de editext
                    // despues se llama la referencia databaseReference y que ponga p, que son
                    // los datos que ya ingrese
                    persona p = new persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(app);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }

            case R.id.icon_save:
            {
                persona p = new persona();
                p.setUid(personaseleccionada.getUid());
                p.setNombre(ed_nombre.getText().toString().trim());
                p.setApellido(ed_apellido.getText().toString().trim());
                p.setCorreo(ed_correo.getText().toString().trim());
                p.setPassword(ed_pass.getText().toString().trim());
                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                vibrator.vibrate(100);
                break;
            }

            case R.id.icon_delete:
            {
                persona p = new persona();
                p.setUid(personaseleccionada.getUid());
                databaseReference.child("Persona").child(p.getUid()).removeValue();
                Toast.makeText(this,"Delete",Toast.LENGTH_LONG).show();
                vibrator.vibrate(100 );
                sonido.start();
                limpiarCajas();
                break;
            }

            default:break;
        }
        return true;
    }

    // Este metodo es logico que hace ajajajjajaj xd
    private void limpiarCajas() {
        ed_nombre.setText("");
        ed_correo.setText("");
        ed_pass.setText("");
        ed_apellido.setText("");

    }

    // Este metodo valida que los campos este llenos
    private void validacion() {
        String nombre = ed_nombre.getText().toString();
        String correo = ed_correo.getText().toString();
        String password = ed_pass.getText().toString();
        String app = ed_apellido.getText().toString();


        if (nombre.equals("")){
            ed_nombre.setError("Required");
        }
        else if (app.equals("")){
            ed_apellido.setError("Required");
        }
        else if (correo.equals("")){
            ed_correo.setError("Required");
        }
        else if (password.equals("")){
            ed_pass.setError("Required");
        }
    }
}
