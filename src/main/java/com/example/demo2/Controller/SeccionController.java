package com.example.demo2.Controller;

import com.example.demo2.Model.Horario;
import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secciones")
public class SeccionController {

    @Autowired
    private SeccionRepository seccionRepository;

    @GetMapping
    public List<Seccion> getSecciones() {
        return seccionRepository.findAll();
    }

    @PostMapping
    public Seccion crearSeccion(@RequestBody Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    @PutMapping("/{id}")
    public Seccion actualizarCupo(@PathVariable int id, @RequestParam int nuevoCupo) {
        return seccionRepository.findById(id).map(seccion -> {
            seccion.actualizarCupo(nuevoCupo);
            return seccionRepository.save(seccion);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarSeccion(@PathVariable int id) {
        seccionRepository.deleteById(id);
    }

    @PostMapping("/{id}/horario")
    public Seccion agregarHorario(@PathVariable int id, @RequestBody Horario horario) {
        return seccionRepository.findById(id).map(seccion -> {
            seccion.agregarHorario(horario);
            return seccionRepository.save(seccion);
        }).orElse(null);
    }
}
