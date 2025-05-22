package com.example.demo2.Controller;

import com.example.demo2.Model.EstadoInscripcion;
import com.example.demo2.Model.EstadoInscripcionEnum;
import com.example.demo2.Model.Inscripcion;
import com.example.demo2.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionRepository inscripcionRepository;





//para obtener una inscripción por id
    @GetMapping("/{id}")
public Inscripcion obtenerInscripcionPorId(@PathVariable int id) {
    return inscripcionRepository.findById(id).orElse(null);
}




//para verificar disponibilidad de una inscripción

@GetMapping("/{id}/estado")
public EstadoInscripcionEnum obtenerEstado(@PathVariable int id) {
    return inscripcionRepository.findById(id)
            .map(Inscripcion::obtenerEstado)
            .orElse(null);
}






    // POST para crear una inscripción con estado inicial PENDIENTE
    @PostMapping
    public Inscripcion crearInscripcion(@RequestBody Inscripcion inscripcion) {
        EstadoInscripcion estado = new EstadoInscripcion();
        estado.setNombreEstado(EstadoInscripcionEnum.PENDIENTE);
        estado.setFechaCambio(new Date());

        inscripcion.setFechaInscripcion(new Date());
        inscripcion.setEstado(estado);

        return inscripcionRepository.save(inscripcion);
    }

    // GET para listar todas las inscripciones
    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    // PUT para cambiar estado de una inscripción
    @PutMapping("/{id}/estado")
    public Inscripcion cambiarEstado(@PathVariable int id, @RequestParam EstadoInscripcionEnum nuevoEstado) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.cambiarEstado(nuevoEstado);
            return inscripcionRepository.save(inscripcion);
        }).orElse(null);
    }
}
