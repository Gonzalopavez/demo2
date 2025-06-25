package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.assembler.SeccionModelAssembler;
import com.example.demo2.dto.SeccionDTO;
import com.example.demo2.service.SeccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Indica que esta clase es un controlador REST
@RestController 


//todos los endpoints de este controlador comienzan con /api/secciones
@RequestMapping("/api/secciones") 


public class SeccionController {


    //inyecta el ensamblador de modelos para convertir entidades a DTOs con HATEOAS

    @Autowired
    private SeccionModelAssembler assembler; 





    //inyecta el servicio de secciones para manejar la lógica de negocio


    @Autowired
    private SeccionService seccionService; 





    

    // 1. Este método guarda una nueva sección en la base de datos.

    @PostMapping("/guardar")
    public ResponseEntity<Seccion> guardarSeccion(@RequestBody Seccion seccion) {
        Seccion nueva = seccionService.guardarSeccion(seccion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // 2. Este método obtiene todas las secciones de la base de datos. IMPLEMENTACION HATEOAS LISTAS

    @GetMapping("/secciones")
public ResponseEntity<CollectionModel<SeccionDTO>> listarSecciones() {
    List<Seccion> secciones = seccionService.obtenerTodas();
    List<SeccionDTO> dtos = secciones.stream()
    .map(assembler::toModel)
    .toList();
    return ResponseEntity.ok(CollectionModel.of(dtos));
}


    // 3. Este método obtiene una sección por su ID. Si no se encuentra, devuelve null. IMPLEMENTACION HATEOAS OBJETO

    @GetMapping("/secciones/{id}")
public ResponseEntity<SeccionDTO> obtenerSeccionPorId(@PathVariable Long id) {
    Seccion seccion = seccionService.obtenerPorId(id);
    if (seccion != null) {
        return ResponseEntity.ok(assembler.toModel(seccion));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    // 4. Este método elimina una sección por su ID. Si no se encuentra, no hace nada.

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSeccion(@PathVariable Long id) {
        seccionService.eliminarSeccion(id);
        return ResponseEntity.noContent().build();
    }



    // 5. Ver cupos disponibles de una sección



@GetMapping("/{id}/cupo-disponible")
public ResponseEntity<Boolean> cupoDisponible(@PathVariable Long id) {
    Seccion seccion = seccionService.obtenerPorId(id);
    if (seccion == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(seccion.esCupoDisponible());
}




    // 6. este metodo actualiza un cupo disponible de una sección
    
@PutMapping("/{id}/actualizar-cupo")
public ResponseEntity<Seccion> actualizarCupoDisponible(
        @PathVariable Long id,
        @RequestParam int nuevoCupo) {
    
    Seccion actualizada = seccionService.actualizarCupoDisponible(id, nuevoCupo);
    
    if (actualizada != null) {
        return ResponseEntity.ok(actualizada);
    } else {
        return ResponseEntity.notFound().build();
    }
}




// 7. actualizar una sección

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










