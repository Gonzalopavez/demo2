//Aqui se implementa la interfaz SeccionService
//EL cómo se debe implementar los metodos de la interfaz SeccionService



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

    
    public Seccion guardarSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

   
    public List<Seccion> obtenerTodas() {
        return seccionRepository.findAll();
    }

    public Seccion obtenerPorId(Long id) {
        return seccionRepository.findById(id).orElse(null);
    }

    
    public void eliminarSeccion(Long id) {
        seccionRepository.deleteById(id);
    }






    
}
