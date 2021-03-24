package com.aprendiendoando.recyclerview_personalizado;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CHENAO on 3/07/2017.
 */

public class AdaptadorPersonajes
        extends RecyclerView.Adapter<AdaptadorPersonajes.ViewHolderPersonajes>
        implements View.OnClickListener{

    // Crea Array de acuerdo al modelo que se creo
    ArrayList<PersonajeVo> listaPersonajes;

    // Evento de Onclick. Oyente
    private View.OnClickListener listener;

    public AdaptadorPersonajes(ArrayList<PersonajeVo> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {

        // De acuerdo al cick le visualizacion de los XML, o grid o list
        // Pero porque a un INT ????
        int layout=0;
        if (Utilidades.visualizacion==Utilidades.LIST){
            layout=R.layout.iem_list_personajes;
        }else {
            layout=R.layout.item_grid_personajes;
        }

        // Le da forma al Layout
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        //
        view.setOnClickListener(this);

        return new ViewHolderPersonajes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {

        // Asigna Nombre a la lista, dependiedo la pos, en como un ciclo
        holder.etiNombre.setText(listaPersonajes.get(position).getNombre());

        if (Utilidades.visualizacion==Utilidades.LIST){
            // Como en la visualizacion grid no hay descripcion
            // Hay que eliminarla, pero eso depende del estado de Visualizacion
            holder.etiInformacion.setText(listaPersonajes.get(position).getInfo());
        }

        // Asigna la foto depende la posicion
        holder.foto.setImageResource(listaPersonajes.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaPersonajes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {

        // Envie la viste si hay un evento click
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

        TextView etiNombre,etiInformacion;
        ImageView foto;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);

            // Referencia el nombre y todos sus objetos


            etiNombre= (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.visualizacion==Utilidades.LIST){
                etiInformacion= (TextView) itemView.findViewById(R.id.idInfo);
            }
            foto= (ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}