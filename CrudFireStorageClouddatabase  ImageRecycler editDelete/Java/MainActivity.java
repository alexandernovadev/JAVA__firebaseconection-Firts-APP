package com.aprendiendoando.subirimagenesfirebase;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;


public class MainActivity extends AppCompatActivity
{

    String nameimagen;

    Uri uriimagengaleria;
    Uri downloadUrifirebase;

    String downloadURLString;
    EditText txt_nombre, txt_apellido;

    Button btn_sorage;
    Button btn_add;
    ImageView foto;
    ProgressBar progresobar;

    ProgressDialog dialog;

    ArrayList<ModeloDatos> lista;
    // Esta Variable es necesaria para subir la imagen a FIREBASE
    // Uploadtask --> tarea de subir
    // pertenece a firebase
    UploadTask tareaupdown;

    // Variable para inicializar Firebase
    private StorageReference refStorage;

    // Variable para que abra la camara

    private static final int GALERIA_INTENT = 1;

    //FireCloudDatabase
    FirebaseFirestore basedatos = FirebaseFirestore.getInstance();
    CollectionReference coleccionref = basedatos.collection("Datos");


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_nombre = (EditText) findViewById(R.id.ed_nombre);
        txt_apellido = (EditText) findViewById(R.id.ed_apellido);


        // Inicializar la instancia del Storage
        refStorage = FirebaseStorage.getInstance().getReference();

        btn_sorage = (Button) findViewById(R.id.btn_add);
        foto= (ImageView) findViewById(R.id.imageView);
        dialog = new ProgressDialog(this);

        progresobar = (ProgressBar) findViewById(R.id.progresbar);


        // Este boton es para guardar los datos en la base de datos Cloud
        btn_add = (Button) findViewById(R.id.btn_enviar);

        btn_add.setEnabled(false);



        // Este btn es para ver los datos
        btn_sorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Esto es para abrir la camara
                Intent intent = new Intent(Intent.ACTION_PICK);

                // Esta linea dice que seleccione todos los tipo de
                // formatos de imagenes que hay
                intent.setType("image/*");
                startActivityForResult(intent,GALERIA_INTENT);

            }
        });

    }



    // Esta funcion es para abrir la galeria y seleccionar una imagen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK && requestCode == GALERIA_INTENT) {

            //Obtiene la imagen
            uriimagengaleria = data.getData();

            // Esto es por puro ejemplo, poner la imagen en un ImageView
            // TODO Cuando se rota el telefono se borra la imagen ????
            //foto.setImageURI(uri);

            subirImagenFire(uriimagengaleria);

        }
    }


    public void subirImagenFire (Uri imagendegaleria)
    {

        // Crea la direccion en FireStorage  --> fotos/....... aqui va la imagen
        // .getLastPathSegment() --> Obtiene el nombre de la imagen
        final StorageReference carpeta = refStorage.child("fotos")
                .child(uriimagengaleria.getLastPathSegment());

        //nameimagen = uriimagengaleria.getLastPathSegment();
        //Log.d("La imagn se llama" , nameimagen);

        // A la variable Upload se le asigna la tarea de subir la foto
        // y se la pasa uri-> que es la foto que saco de galeria
        tareaupdown = carpeta.putFile(uriimagengaleria);


        // Los siguientes metodo me informan error y succes
        // Para ello se crea una variable TASK<Uri> --> que hace la verificacion
        // de putfile() --> osea "tareaupdown"

        // Esta funciones son DOS : .continueWithTask y .addOnCompleteListener

        // Las tareas retornan valores asincronos


        tareaupdown.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progresobar.setProgress(0);
                    }
                }, 500);


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progresobar.setProgress((int) progress);


            }
        });

        Task<Uri> urlTask = tareaupdown
                .continueWithTask(new Continuation< UploadTask.TaskSnapshot, Task<Uri> >()
                {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.
                            TaskSnapshot> task) throws Exception
                    {

                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        //Log.i("QUE ES ESTOO" , String.valueOf(carpeta.getDownloadUrl()));

                        // Continue with the task to get the download URL
                        return carpeta.getDownloadUrl();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Uri>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {


                        if (task.isSuccessful())
                        {

                            downloadUrifirebase = task.getResult();
                            downloadURLString = downloadUrifirebase.toString();

                            Log.i("MMEENNSSAAJEEEE", downloadURLString);

                            //dialog.dismiss();

                            RequestOptions options = new RequestOptions();
                            options.centerCrop().fitCenter();
                            Glide.with(MainActivity.this)
                                    .load(downloadURLString)
                                    .apply(options)
                                    .into(foto);

                            btn_add.setEnabled(true);
                            Toast.makeText(MainActivity.this,
                                    "SE Suibio imagen", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            // Si no es completada la tarea, o seria como onFailure ?
                        }
                    }
                });

    }


    // Esta funcion envia los datos a la base de datos
    public void click_btn (View view)
    {
        String ID = UUID.randomUUID().toString();
        String nombre = txt_nombre.getText().toString();
        String apellido = txt_apellido.getText().toString();

        ModeloDatos datos = new ModeloDatos(ID,nombre, apellido, downloadURLString, nameimagen);

        coleccionref.document(ID).set(datos).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this,
                        "Dato Subido a Firebase", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void btn_verdatos(View view)
    {
        Intent intent = new Intent(this, Ventanta_ListDatos.class);
        startActivity(intent);

    }


}