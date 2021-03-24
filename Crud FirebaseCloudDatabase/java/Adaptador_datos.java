package com.aprendiendoando.firebasecloud;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Adaptador_datos extends RecyclerView.Adapter<Adaptador_datos.DatosViewHolder> 
{
//Esta en Git
    
    ArrayList<ModeloDatos> arrayList_modelo;
    View vista;

    public Adaptador_datos(ArrayList<ModeloDatos> arrayList_modelo) 
    {
        this.arrayList_modelo = arrayList_modelo;
    }

    public Adaptador_datos() 
    {
    }

    @NonNull
    @Override
    public DatosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int posicion) 
    {
        vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);
        return new DatosViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull DatosViewHolder datosViewHolder, int posicion) 
    {
        datosViewHolder.tv_nombre.setText(arrayList_modelo.get(posicion).getNombre());
        datosViewHolder.tv_apellido.setText(arrayList_modelo.get(posicion).getApellido());
    }

    @Override
    public int getItemCount() 
    {
        return arrayList_modelo.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder 
    {

        TextView tv_nombre, tv_apellido;

        public DatosViewHolder(@NonNull View itemView) 
        {
            super(itemView);

            tv_nombre = (TextView) itemView.findViewById(R.id.ed_nombre);
            tv_apellido = (TextView) itemView.findViewById(R.id.ed_apellido);

        }
    }
}
