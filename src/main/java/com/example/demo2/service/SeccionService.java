
//Interfaz, qué metodos deben existir en el servicio
//Aqui se define qué operaciones se pueden hacer con una seccion


package com.example.demo2.service;
import com.example.demo2.Model.Seccion;
import java.util.List;


public interface SeccionService {


    Seccion guardarSeccion(Seccion seccion);
    List<Seccion> obtenerTodas();
    Seccion obtenerPorId(Long id);
    void eliminarSeccion(Long id);


    
}
