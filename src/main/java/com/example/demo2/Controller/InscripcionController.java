package com.example.demo2.Controller;

import com.example.demo2.Model.Inscripcion;
import com.example.demo2.Model.EstadoInscripcionEnum;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")



public class InscripcionController {

    private List<Inscripcion> inscripciones = new ArrayList<>();





    // para obtener las inscricpiones que existen
    // en la base de datos

    @GetMapping
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }






    // para crear una nueva inscripción
    // se le asigna la fecha de inscripción y el estado por defecto}


    @PostMapping
    public Inscripcion crearInscripcion(@RequestBody Inscripcion inscripcion) {
        inscripcion.setFechaInscripcion(new Date());
        inscripcion.setEstado(EstadoInscripcionEnum.PENDIENTE);
        inscripciones.add(inscripcion);
        return inscripcion;
    }









    // aqui puedo Activar una inscripción

    @PutMapping("/{id}/activar")
    public Inscripcion activarInscripcion(@PathVariable int id) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId() == id) {
                inscripcion.activar();
                return inscripcion;
            }
        }
        return null;
    }





    // aqui puedo Completar una inscripción

    @PutMapping("/{id}/completar")
    public Inscripcion completarInscripcion(@PathVariable int id) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId() == id) {
                inscripcion.completar();
                return inscripcion;
            }
        }
        return null;
    }






    // aqui puedo Cancelar una inscripción

    @PutMapping("/{id}/cancelar")
    public Inscripcion cancelarInscripcion(@PathVariable int id) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId() == id) {
                inscripcion.cancelar();
                return inscripcion;
            }
        }
        return null;
    }






    // aqui puedo Cambiar el estado manualmente, literal escribir el estado...

    @PutMapping("/{id}/cambiarEstado")
    public Inscripcion cambiarEstado(@PathVariable int id, @RequestParam EstadoInscripcionEnum nuevoEstado) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId() == id) {
                inscripcion.cambiarEstado(nuevoEstado);
                return inscripcion;
            }
        }
        return null;
    }





    // Obtener el estado actual de la inscripción
    // Se puede usar para verificar si la inscripción está activa o no

    @GetMapping("/{id}/estado")
    public String obtenerEstado(@PathVariable int id) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId() == id) {
                return "El estado actual es: " + inscripcion.obtenerEstado();
            }
        }
        return "Inscripción no encontrada.";
    }





    // aqui puedo Eliminar una inscripción

    @DeleteMapping("/{id}")
    public void eliminarInscripcion(@PathVariable int id) {
        inscripciones.removeIf(inscripcion -> inscripcion.getId() == id);
    }
}
