package com.example.demo2.service;

import com.example.demo2.Model.Seccion;
import java.util.List;

public interface SeccionService {

    Seccion guardarSeccion(Seccion seccion);
    List<Seccion> obtenerTodas();
    Seccion obtenerPorId(Long id);
    void eliminarSeccion(Long id);


    
}
