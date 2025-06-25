package com.example.demo2.service;


import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import com.example.demo2.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service // Anotación que indica que esta clase es un servicio de Spring

public class SeccionService {


    // Inyección de dependencias para acceder al repositorio de Sección
    // Esto permite realizar operaciones CRUD sobre la entidad Sección en la base de datos
    @Autowired
    private SeccionRepository seccionRepository;



                           // Métodos del servicio SeccionService




    // 1. Este método guarda una nueva sección en la base de datos.

    public Seccion guardarSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }


    // 2. Este método obtiene todas las secciones de la base de datos.


    public List<Seccion> obtenerTodas() {
        return seccionRepository.findAll();
    }

    // 3.  Este método obtiene una sección por su ID. Si no se encuentra, devuelve null.

    public Seccion obtenerPorId(Long id) {
        return seccionRepository.findById(id).orElse(null);
    }

    // 4. Este método elimina una sección por su ID. Si no se encuentra, no hace nada.

    
    public void eliminarSeccion(Long id) {
        seccionRepository.deleteById(id);
    }


    // 5. Ver cupos disponibles de una sección, por id , si no existe la sección lanza error

    public boolean hayCupoDisponible(Long id) {
    Seccion seccion = obtenerPorId(id);
    if (seccion == null) {
        throw new IllegalArgumentException("Sección no encontrada");
    }
    return seccion.esCupoDisponible();
}

    // 6. actualizar cupos disponibles de una sección


    public Seccion actualizarCupoDisponible(Long id, int nuevoCupo) {
    Seccion seccion = obtenerPorId(id);
    if (seccion == null) {
        return null;
    }

    seccion.setCupoDisponible(nuevoCupo);
    return guardarSeccion(seccion);
}





    // 7. Este método actualiza una sección existente, si existe..se actualizan sus atributos y 
    //se guarda de nuevo en la base de datos.
    //si no existe, devuelve null.


    public Seccion actualizarSeccion(Long id, Seccion seccionNueva) {
    Seccion existente = obtenerPorId(id);
    if (existente == null) {
        return null;
    }

    existente.setNombre(seccionNueva.getNombre());
    existente.setIdCurso(seccionNueva.getIdCurso());
    existente.setIdDocente(seccionNueva.getIdDocente());
    existente.setCupoMaximo(seccionNueva.getCupoMaximo());
    existente.setCupoDisponible(seccionNueva.getCupoDisponible());
    existente.setHorarios(seccionNueva.getHorarios());

    return guardarSeccion(existente);
}








    
}
