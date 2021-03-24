package com.aprendiendoando.maestrodetalle.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aprendiendoando.maestrodetalle.R;
import com.aprendiendoando.maestrodetalle.adaptadores.AdaptadorPersonajes;
import com.aprendiendoando.maestrodetalle.entidades.PersonajeVO;
import com.aprendiendoando.maestrodetalle.interfaces.IComunica;

import java.util.ArrayList;


public class ListaPersonajeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<PersonajeVO> listapersonajes;

    RecyclerView recyclerViewpersonajes;


    Activity activity;
    IComunica interfazcomunicadora;

    // Esto sirve para crear una actividad nueva
    // Esto se ejecuta en OnAtach


    public ListaPersonajeFragment() {

    }

    public static ListaPersonajeFragment newInstance(String param1, String param2) {
        ListaPersonajeFragment fragment = new ListaPersonajeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_lista_personaje, container, false);

        listapersonajes = new ArrayList<>();

        recyclerViewpersonajes = (RecyclerView) vista.findViewById(R.id.recyclerId);

        recyclerViewpersonajes.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        AdaptadorPersonajes adapter = new AdaptadorPersonajes(listapersonajes);
        recyclerViewpersonajes.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Selecciono: " +
                        listapersonajes.get(recyclerViewpersonajes.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();

                // Aca enviamos el objeto completo
                interfazcomunicadora.enviarPersonaje(listapersonajes.get(recyclerViewpersonajes.getChildAdapterPosition(v)));
            }
        });


        return vista;
    }

    private void llenarListaPersonajes() {

        listapersonajes.add(new PersonajeVO(getString(R.string.goku_nombre), getString(R.string.goku_descripcion_corta),
                getString(R.string.goku_descripcion_Larga), R.drawable.goku_cara,R.drawable.goku_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.gohan_nombre), getString(R.string.gohan_descripcion_corta),
                getString(R.string.gohan_descripcion_Larga), R.drawable.gohan_cara,R.drawable.gohan_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.goten_nombre), getString(R.string.goten_descripcion_corta),
                getString(R.string.goten_descripcion_Larga), R.drawable.goten_cara,R.drawable.goten_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.krilin_nombre), getString(R.string.krilin_descripcion_corta),
                getString(R.string.krilin_descripcion_Larga), R.drawable.krilin_cara,R.drawable.krilin_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.picoro_nombre), getString(R.string.picoro_descripcion_corta),
                getString(R.string.picoro_descripcion_Larga), R.drawable.picoro_cara,R.drawable.picoro_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.trunks_nombre), getString(R.string.trunks_descripcion_corta),
                getString(R.string.trunks_descripcion_Larga), R.drawable.trunks_cara,R.drawable.trunks_detalle));

        listapersonajes.add(new PersonajeVO(getString(R.string.vegueta_nombre), getString(R.string.vegueta_descripcion_corta),
                getString(R.string.vegueta_descripcion_Larga), R.drawable.vegueta_cara,R.drawable.vegueta_detalle));
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        // Si el contexto que le esta llegando es una instancia de un
        //activity entonces....
        if(context instanceof Activity){

            this.activity = (Activity) context;
            interfazcomunicadora = (IComunica ) this.activity;


        }
        // Esto de arriva sirve para hacer la comunicacion entre la lista y la
        // listadetalle



        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
