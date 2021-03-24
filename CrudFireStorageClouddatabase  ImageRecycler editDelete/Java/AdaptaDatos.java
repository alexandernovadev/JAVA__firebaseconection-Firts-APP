package com.aprendiendoando.subirimagenesfirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptaDatos extends RecyclerView.Adapter<AdaptaDatos.Vistaholder>
        implements View.OnClickListener
{
    // Este Context es muy importante, ya que referncia la actividad
    // Es importante pasar el parametro en la actividad que lo enlaza
    // Ahhh! y construir el contructor con eso.
    private Context context;
    private ArrayList<ModeloDatos> arraydatos;


    // Evento de Onclick. Oyente
    private View.OnClickListener listener;


    public AdaptaDatos(){
    }

    public AdaptaDatos(ArrayList<ModeloDatos> arraydatos,Context context) {
        this.context = context;
        this.arraydatos = arraydatos;
    }

    @NonNull
    @Override
    public Vistaholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos)
    {
        View vista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.items_datos,viewGroup,false);

        vista.setOnClickListener(this);

        return new Vistaholder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Vistaholder vistaholder, int pos)
    {
        vistaholder.nombre.setText(arraydatos.get(pos).getNombre());
        vistaholder.apellido.setText(arraydatos.get(pos).getApellido());

        Glide.with(context)
             .load(arraydatos.get(pos).getUrlimagen())
             .into(vistaholder.imagen);
    }


    @Override
    public int getItemCount() {
        return arraydatos.size();
    }

    @Override
    public void onClick(View v) {
        // Envie la viste si hay un evento click
        if (listener!=null){
            listener.onClick(v);
        }
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }


    public class Vistaholder extends RecyclerView.ViewHolder
    {
        TextView nombre,apellido;
        ImageView imagen;

        public Vistaholder(@NonNull View itemView)
        {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre_item);
            apellido = (TextView) itemView.findViewById(R.id.apellido_itemm);
            imagen = (ImageView) itemView.findViewById(R.id.imagen_item);
        }
    }
}
