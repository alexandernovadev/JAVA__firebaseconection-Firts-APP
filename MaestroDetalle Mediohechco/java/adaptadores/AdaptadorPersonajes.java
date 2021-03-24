package com.aprendiendoando.maestrodetalle.adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiendoando.maestrodetalle.R;
import com.aprendiendoando.maestrodetalle.entidades.PersonajeVO;

import java.util.ArrayList;

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.PersonajesViewHolder> implements View.OnClickListener {


    ArrayList<PersonajeVO>  listapersonajes;
    private View.OnClickListener listener;
    View view;

    public AdaptadorPersonajes(ArrayList<PersonajeVO> listapersonajes) {
        this.listapersonajes = listapersonajes;
    }


    public AdaptadorPersonajes() {

    }

    @NonNull
    @Override
    public PersonajesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,null,false);

        view.setOnClickListener(this);
        return new PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajesViewHolder personajesViewHolder, int posicion) {

        personajesViewHolder.tvnombre.setText(listapersonajes.get(posicion).getNombre());
        personajesViewHolder.tvdetalle.setText(listapersonajes.get(posicion).getInfo());
        personajesViewHolder.foto.setImageResource(listapersonajes.get(posicion).getImagenId());
    }

    @Override
    public int getItemCount() {
        return listapersonajes.size();
    }

    @Override
    public void onClick(View vista) {
        if (listener!=null){
            listener.onClick(vista);
        }
    }

    public  void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;

    }


    public class PersonajesViewHolder extends RecyclerView.ViewHolder {

        TextView tvnombre,tvdetalle;
        ImageView foto;

        public PersonajesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvnombre = (TextView) itemView.findViewById(R.id.idNombre);
            tvdetalle = (TextView) itemView.findViewById(R.id.idInfo);
            foto = (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
