package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.Model.Horario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/secciones")


public class SeccionController {

    private List<Seccion> secciones = new ArrayList<>();

    @GetMapping
    public List<Seccion> getSecciones() {
        return secciones;
    }

    @PostMapping
    public Seccion crearSeccion(@RequestBody Seccion seccion) {
        secciones.add(seccion);
        return seccion;
    }

    @PutMapping("/{id}")
    public Seccion actualizarCupo(@PathVariable int id, @RequestParam int nuevoCupo) {
        for (Seccion seccion : secciones) {
            if (seccion.getId() == id) {
                seccion.actualizarCupo(nuevoCupo);
                return seccion;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminarSeccion(@PathVariable int id) {
        secciones.removeIf(seccion -> seccion.getId() == id);
    }

    @PostMapping("/{id}/horario")
    public Seccion agregarHorario(@PathVariable int id, @RequestBody Horario horario) {
        for (Seccion seccion : secciones) {
            if (seccion.getId() == id) {
                seccion.agregarHorario(horario);
                return seccion;
            }
        }
        return null;
    }
}
