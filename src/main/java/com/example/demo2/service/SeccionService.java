



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

    // Este método guarda una nueva sección en la base de datos.

    public Seccion guardarSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }


    // Este método obtiene todas las secciones de la base de datos.


    public List<Seccion> obtenerTodas() {
        return seccionRepository.findAll();
    }

    // Este método obtiene una sección por su ID. Si no se encuentra, devuelve null.

    public Seccion obtenerPorId(Long id) {
        return seccionRepository.findById(id).orElse(null);
    }

    // Este método elimina una sección por su ID. Si no se encuentra, no hace nada.

    
    public void eliminarSeccion(Long id) {
        seccionRepository.deleteById(id);
    }


    
    // el motodo del controller que actualiza el cupo disponible de una sección, no se encuentra aqui
    //ya que se encuentra en el controller, pero se implementa en la interfaz SeccionService
    //no es necesario implementarlo aqui porqu no usa una logica de negocio compleja, solo actualiza el cupo disponible de una sección






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
