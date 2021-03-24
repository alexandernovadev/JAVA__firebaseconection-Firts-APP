package com.aprendiendoando.firebaseimageslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.DatosViewHolder> {


    ArrayList<Datos_modelo> arrayList_datosmodelo;
    View vista;

    public AdaptadorDatos(ArrayList<Datos_modelo> arrayList_datosmodelo) {
        this.arrayList_datosmodelo = arrayList_datosmodelo;
    }

    public AdaptadorDatos() {

    }

    @NonNull
    @Override
    public DatosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int posicionvh) {

        vista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list,null,false);
        return new DatosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosViewHolder datosViewHolder, int posicion) {

        datosViewHolder.tv_nombre.setText(arrayList_datosmodelo.get(posicion).getNombre());
        datosViewHolder.tv_apellido.setText(arrayList_datosmodelo.get(posicion).getApellido());

    }

    @Override
    public int getItemCount() {
        return arrayList_datosmodelo.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre,tv_apellido;

        public DatosViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_nombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            tv_apellido = (TextView) itemView.findViewById(R.id.tv_apellido);

        }
    }
}
