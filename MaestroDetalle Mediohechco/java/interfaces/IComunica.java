package com.aprendiendoando.maestrodetalle.interfaces;

import com.aprendiendoando.maestrodetalle.entidades.PersonajeVO;

public interface IComunica {

    // Esto comunica los fragments, no lleva llaves ???
    public void enviarPersonaje(PersonajeVO personaje);
}
