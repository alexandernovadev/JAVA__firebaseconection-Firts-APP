package com.aprendiendoando.subirimagenesfirebase;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class edit_item extends AppCompatActivity
{

    String id;
    String nameimageolder;

    ModeloDatos datositem_edit = null;
    ImageView btn_done, foto;
    Button btn_chooseimage;
    EditText edit_name, edit_apellido;
    private static final int GALERIA_INTENT = 1;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    //FireCloudDatabase
    FirebaseFirestore basedatos = FirebaseFirestore.getInstance();
    CollectionReference coleccionref = basedatos.collection("Datos");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        btn_done = (ImageView) findViewById(R.id.brn_confirm_edit);
        btn_chooseimage = (Button) findViewById(R.id.btn_selecionar_imagen);
        edit_name = (EditText) findViewById(R.id.nombre_edit);
        edit_apellido = (EditText) findViewById(R.id.apellido_edit);
        foto = (ImageView) findViewById(R.id.imagepreview_edit);

        final Bundle datosdeedit = getIntent().getExtras();


        if (datosdeedit != null)
        {
            datositem_edit = (ModeloDatos) datosdeedit.getSerializable("Itemedit");


            Log.d("ETEROOOO " , "SI ESTA ADENTROOOOO pu u ru ru");
            id= datositem_edit.getID();
            nameimageolder= datositem_edit.getNameimagen();
            edit_name.setText(datositem_edit.getNombre().toString());
            edit_apellido.setText(datositem_edit.getApellido().toString());

            String urlftorecibida = datositem_edit.getUrlimagen();

            Glide.with(this).load(urlftorecibida).into(foto);
        }

        btn_chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                startActivityForResult(intent,GALERIA_INTENT);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALERIA_INTENT)
        {
            final Uri uriimage= data.getData();


            StorageReference storageRef = storage.getReference();
            final StorageReference ref = storageRef.child("fotos")
                    .child(uriimage.getLastPathSegment());
            UploadTask uploadTask = ref.putFile(uriimage);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {



                        Uri downloadUri = task.getResult();
                        String ulrnew = downloadUri.toString();
                        ModeloDatos newupdate = new ModeloDatos(
                                id,
                                edit_name.getText().toString(),
                                edit_apellido.getText().toString(),
                                ulrnew,
                                uriimage.getLastPathSegment()
                        );

                        coleccionref.document(id).set(newupdate);
                        Toast.makeText(edit_item.this,
                                "Se edito esta mierda", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(edit_item.this, Ventanta_ListDatos.class);
                        startActivity(intent);

                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });
        }
    }
}



