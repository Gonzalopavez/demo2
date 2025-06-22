



package com.example.demo2.service;
import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import com.example.demo2.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service

public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepository;

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


    // 5. Ver cupos disponibles de una sección

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





    // Este método actualiza una sección existente. Si la sección no existe, devuelve null.


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
