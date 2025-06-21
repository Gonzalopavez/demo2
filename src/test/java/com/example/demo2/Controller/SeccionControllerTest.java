package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.service.SeccionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeccionController.class)
class SeccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeccionService seccionService;

    @Autowired
    private ObjectMapper objectMapper;

    // 1. GUARDAR
    @Test
    void testGuardarSeccion() throws Exception {
        Seccion seccion = new Seccion();
        seccion.setId(1L);
        seccion.setNombre("Sección A");

        when(seccionService.guardarSeccion(any(Seccion.class))).thenReturn(seccion);

        mockMvc.perform(post("/api/secciones/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(seccion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Sección A"));
    }

    // 2. LISTAR TODAS
    @Test
    void testListarSecciones() throws Exception {
        Seccion s1 = new Seccion();
        s1.setId(1L);
        s1.setNombre("Sección A");

        Seccion s2 = new Seccion();
        s2.setId(2L);
        s2.setNombre("Sección B");

        when(seccionService.obtenerTodas()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/api/secciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // 3. OBTENER POR ID
    @Test
    void testObtenerPorId() throws Exception {
        Seccion seccion = new Seccion();
        seccion.setId(1L);
        seccion.setNombre("Sección A");

        when(seccionService.obtenerPorId(1L)).thenReturn(seccion);

        mockMvc.perform(get("/api/secciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Sección A"));
    }

    // 4. ELIMINAR
    @Test
    void testEliminarSeccion() throws Exception {
        doNothing().when(seccionService).eliminarSeccion(1L);

        mockMvc.perform(delete("/api/secciones/1"))
                .andExpect(status().isNoContent());
    }

    // 5. CUPO DISPONIBLE - TRUE 
    @Test
    void testCupoDisponible_True() throws Exception {
        Seccion seccion = new Seccion();
        seccion.setId(1L);
        seccion.setCupoDisponible(5); // tiene cupos

        when(seccionService.obtenerPorId(1L)).thenReturn(seccion);

        mockMvc.perform(get("/api/secciones/1/cupo-disponible"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    // 6. CUPO DISPONIBLE - FALSE
    @Test
    void testCupoDisponible_False() throws Exception {
        Seccion seccion = new Seccion();
        seccion.setId(2L);
        seccion.setCupoDisponible(0); // no hay cupos

        when(seccionService.obtenerPorId(2L)).thenReturn(seccion);

        mockMvc.perform(get("/api/secciones/2/cupo-disponible"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    // 7. CUPO DISPONIBLE - NO EXISTE
    @Test
    void testCupoDisponible_SeccionNoExiste() throws Exception {
        when(seccionService.obtenerPorId(99L)).thenReturn(null); // no existe

        mockMvc.perform(get("/api/secciones/99/cupo-disponible"))
                .andExpect(status().isNotFound());
    }

    @Test
void testActualizarSeccion() throws Exception {
    // Simular sección existente (como si ya estuviera en la BD)
    Seccion seccionExistente = new Seccion();
    seccionExistente.setId(1L);
    seccionExistente.setNombre("Original");
    seccionExistente.setIdCurso(1);
    seccionExistente.setIdDocente(1);
    seccionExistente.setCupoMaximo(30);
    seccionExistente.setCupoDisponible(25);
    seccionExistente.setHorarios(new ArrayList<>());

    // Simular la nueva versión de la sección (lo que llega por el cuerpo del PUT)
    Seccion seccionActualizada = new Seccion();
    seccionActualizada.setId(1L);
    seccionActualizada.setNombre("Actualizada");
    seccionActualizada.setIdCurso(1);
    seccionActualizada.setIdDocente(1);
    seccionActualizada.setCupoMaximo(30);
    seccionActualizada.setCupoDisponible(20);
    seccionActualizada.setHorarios(new ArrayList<>());

    // Simular la lógica del servicio
    when(seccionService.obtenerPorId(1L)).thenReturn(seccionExistente);
    when(seccionService.guardarSeccion(any(Seccion.class))).thenReturn(seccionActualizada);

    // Ejecutar el PUT y verificar el resultado
    mockMvc.perform(put("/api/secciones/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(seccionActualizada)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Actualizada"));
}


} 
