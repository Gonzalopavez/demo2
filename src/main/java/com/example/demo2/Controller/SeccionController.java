package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.service.SeccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secciones")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    @PostMapping("/guardar")
    public ResponseEntity<Seccion> guardarSeccion(@RequestBody Seccion seccion) {
        Seccion nueva = seccionService.guardarSeccion(seccion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Seccion> listarSecciones() {
        return seccionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seccion> obtenerSeccionPorId(@PathVariable Long id) {
        Seccion seccion = seccionService.obtenerPorId(id);
        if (seccion != null) {
            return ResponseEntity.ok(seccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }
}
