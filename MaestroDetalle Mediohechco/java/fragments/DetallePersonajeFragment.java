package com.aprendiendoando.maestrodetalle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiendoando.maestrodetalle.R;
import com.aprendiendoando.maestrodetalle.entidades.PersonajeVO;


public class DetallePersonajeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView tvdetalle;
    ImageView foto;

    public DetallePersonajeFragment() {

    }


    public static DetallePersonajeFragment newInstance(String param1, String param2) {
        DetallePersonajeFragment fragment = new DetallePersonajeFragment();
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

        View view =inflater.inflate(R.layout.fragment_detalle_perspnaje, container, false);

        tvdetalle = (TextView) view.findViewById(R.id.descripcionId);
        foto= (ImageView) view.findViewById(R.id.imagenDetalleId);

        // Recibo la informacion con un Bundle

        Bundle recibirdatos = getArguments();

        PersonajeVO personaje = null;

        if (recibirdatos!= null){

            //Asignando el objeto que se creo
            personaje = (PersonajeVO) recibirdatos.getSerializable("objeto");

            foto.setImageResource(personaje.getImagenId());
            tvdetalle.setText(personaje.getDescripcion());
        }

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
