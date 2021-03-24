package com.aprendiendoando.subirimagenesfirebase;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetalleDato extends AppCompatActivity
{

    TextInputLayout apellid;

    ModeloDatos datositem = null;

    ImageView foto, btnback, btnedit, btndelete;

    TextView iddetalle;
    EditText nombre,apellido;
    TextInputLayout inpuNombre;

    FirebaseFirestore basedatos = FirebaseFirestore.getInstance();
    CollectionReference coleccionref = basedatos.collection("Datos");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dato);


        nombre = (EditText) findViewById(R.id.nombre_detalle);
        apellido = (EditText) findViewById(R.id.apellido_detalle);
        foto = (ImageView) findViewById(R.id.foto_detalle);
        btnback = (ImageView) findViewById(R.id.btnback_detalles);
        btnedit = (ImageView) findViewById(R.id.btnedit_detalles);
        iddetalle = (TextView) findViewById(R.id.id_detalle);
        btndelete = (ImageView) findViewById(R.id.btn_detele_detalle);


        // Se recibe el bundle y se comprueba que no esta vacio
        final Bundle recibirdatos = getIntent().getExtras();

        if (recibirdatos != null)
        {
            // Se hace un casting del modelo aignando el bundle recibido
            datositem = (ModeloDatos) recibirdatos.getSerializable("Item");

            iddetalle.setText(datositem.getID());
            nombre.setText(datositem.getNombre().toString());
            apellido.setText(datositem.getApellido().toString());


            String urlpagina= datositem.getUrlimagen().toString();

            Glide.with(this).load(urlpagina).into(foto);
        }


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleDato.this, edit_item.class);

                datositem = (ModeloDatos) recibirdatos.getSerializable("Item");
                Bundle bundle=new Bundle();
                bundle.putSerializable("Itemedit",datositem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datositem = (ModeloDatos) recibirdatos.getSerializable("Item");
                coleccionref.document(datositem.getID()).delete();


                String nameimg = datositem.getNameimagen();
                Toast.makeText(DetalleDato.this,
                        "SE BORRO", Toast.LENGTH_LONG).show();

                Log.i("El dato que pas es ",nameimg);
                StorageReference storageRef = storage.getReference();

                final StorageReference desertRefdelete = storageRef.child("fotos").child(nameimg);

                // Delete the file
                desertRefdelete.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("BORROOOO", "Se borro");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Uh-oh, an error occurred!
                    }
                });



                Intent intent = new Intent(DetalleDato.this ,Ventanta_ListDatos.class);
                startActivity(intent);
            }
        });

        disableEditText(nombre);
        disableEditText(apellido);

        //apellid = (TextInputLayout) findViewById(R.id.inputDesingapellido);

        //apellid.setDefaultHintTextColor(ColorStateList.valueOf(Color.BLUE));

    }


    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
        //editText.setTextColor(Color.BLACK);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
