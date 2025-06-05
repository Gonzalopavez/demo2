package com.example.demo2.service.impl;

import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import com.example.demo2.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SeccionServiceImpl implements SeccionService {

     @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public Seccion guardarSeccion(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    @Override
    public List<Seccion> obtenerTodas() {
        return seccionRepository.findAll();
    }

    @Override
    public Seccion obtenerPorId(Long id) {
        return seccionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarSeccion(Long id) {
        seccionRepository.deleteById(id);
    }






    
}
