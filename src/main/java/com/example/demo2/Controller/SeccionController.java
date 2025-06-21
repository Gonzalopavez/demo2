package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.service.SeccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@RequestMapping("/api/secciones")
public class SeccionController {

    @Autowired
    private SeccionService seccionService;

    // Este método guarda una nueva sección en la base de datos.

    @PostMapping("/guardar")
    public ResponseEntity<Seccion> guardarSeccion(@RequestBody Seccion seccion) {
        Seccion nueva = seccionService.guardarSeccion(seccion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Este método obtiene todas las secciones de la base de datos.

    @GetMapping
    public List<Seccion> listarSecciones() {
        return seccionService.obtenerTodas();
    }

    // Este método obtiene una sección por su ID. Si no se encuentra, devuelve null.

    @GetMapping("/{id}")
    public ResponseEntity<Seccion> obtenerSeccionPorId(@PathVariable Long id) {
        Seccion seccion = seccionService.obtenerPorId(id);
        if (seccion != null) {
            return ResponseEntity.ok(seccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Este método elimina una sección por su ID. Si no se encuentra, no hace nada.

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }

    //este metodo actualiza un cupo disponible de una sección
    
@PutMapping("/{id}/actualizar-cupo")
public ResponseEntity<Seccion> actualizarCupoDisponible(
        @PathVariable Long id,
        @RequestParam int nuevoCupo) {

    Seccion seccion = seccionService.obtenerPorId(id);
    if (seccion == null) {
        return ResponseEntity.notFound().build();
    }

    seccion.setCupoDisponible(nuevoCupo);
    Seccion actualizada = seccionService.guardarSeccion(seccion);
    return ResponseEntity.ok(actualizada);
}



//actualizar una sección

    @PutMapping("/{id}")
    public ResponseEntity<Seccion> actualizarSeccion(@PathVariable Long id, @RequestBody Seccion seccion) {
        Seccion seccionExistente = seccionService.obtenerPorId(id);
        if (seccionExistente == null) {
            return ResponseEntity.notFound().build();
        }

        seccionExistente.setNombre(seccion.getNombre());
        seccionExistente.setIdCurso(seccion.getIdCurso());
        seccionExistente.setIdDocente(seccion.getIdDocente());
        seccionExistente.setCupoMaximo(seccion.getCupoMaximo());
        seccionExistente.setCupoDisponible(seccion.getCupoDisponible());
        seccionExistente.setHorarios(seccion.getHorarios());

        Seccion actualizada = seccionService.guardarSeccion(seccionExistente);
        return ResponseEntity.ok(actualizada);
    }


}










