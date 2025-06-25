package com.example.demo2.Controller;

import com.example.demo2.Model.Seccion;
import com.example.demo2.assembler.SeccionModelAssembler;
import com.example.demo2.dto.SeccionDTO;
import com.example.demo2.service.SeccionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeccionController.class)
class SeccionControllerTest {



    //Objeto de spring para simular peticiones HTTP a los endpoints del controlador
    @Autowired
    private MockMvc mockMvc;


    //Le dice a spring que use una version simulada del servicio SeccionService ( un mock)
    @MockBean
    private SeccionService seccionService;


    //Lo mismo para el assembler HATEOAS
    @MockBean
    private SeccionModelAssembler assembler;


    //Ayuda para convertir objetos a JSON y viceversa
    @Autowired
    private ObjectMapper objectMapper;




    // 1. GUARDAR
    //Se simula guardar una seccion y se verifica que se retorne el objeto creado con el estado 201 (CREATED)
    //y que el nombre de la sección sea "Sección A"

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
    //Simula 2 secciones y verifica que se retorne una lista con 2 elementos
    // se convierte cada Seccion a SeccionDTO usando el assembler
    //se espera que el endpoint devuelva una lista 
    @Test
    void testListarSecciones() throws Exception {
        Seccion s1 = new Seccion();
        s1.setId(1L);
        s1.setNombre("Sección A");

        Seccion s2 = new Seccion();
        s2.setId(2L);
        s2.setNombre("Sección B");

        when(seccionService.obtenerTodas()).thenReturn(Arrays.asList(s1, s2));

        SeccionDTO dto1 = new SeccionDTO();
        dto1.setId(1L);
        dto1.setNombre("Sección A");

        SeccionDTO dto2 = new SeccionDTO();
        dto2.setId(2L);
        dto2.setNombre("Sección B");

    when(assembler.toModel(s1)).thenReturn(dto1);
    when(assembler.toModel(s2)).thenReturn(dto2);

    mockMvc.perform(get("/api/secciones/secciones"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.seccionDTOList.length()").value(2))
        .andExpect(jsonPath("$._embedded.seccionDTOList[0].nombre").value("Sección A"))
        .andExpect(jsonPath("$._embedded.seccionDTOList[1].nombre").value("Sección B"));
        // esto verifica que la lista tenga 2 elementos
}
    





    // 3. OBTENER POR ID
    @Test
void testObtenerPorId() throws Exception {
    Seccion seccion = new Seccion();
    seccion.setId(1L);
    seccion.setNombre("Sección A");

    SeccionDTO dto = new SeccionDTO();
    dto.setId(1L);
    dto.setNombre("Sección A");

    when(seccionService.obtenerPorId(1L)).thenReturn(seccion);
    when(assembler.toModel(seccion)).thenReturn(dto);

    mockMvc.perform(get("/api/secciones/secciones/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("Sección A")); // esto debería funcionar si el dto está bien
}








    // 4. ELIMINAR
    //simula que se elimina una sección con ID 1 y verifica que se retorne un estado 204 (NO CONTENT)
    @Test
    void testEliminarSeccion() throws Exception {
        doNothing().when(seccionService).eliminarSeccion(1L);

        mockMvc.perform(delete("/api/secciones/1"))
                .andExpect(status().isNoContent());
    }





    //prueba 3 situaciones : hay cupos, no hay cupos y la sección no existe




    // 5. VER CUPO DISPONIBLE - TRUE 
@Test
void testCupoDisponible_True() throws Exception {
    Seccion seccion = new Seccion();
    seccion.setId(1L);
    seccion.setCupoDisponible(5); // Tiene cupos disponibles

    when(seccionService.obtenerPorId(1L)).thenReturn(seccion);

    mockMvc.perform(get("/api/secciones/1/cupo-disponible"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
}


    // CUPO DISPONIBLE - FALSE

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


    // CUPO DISPONIBLE - NO EXISTE
    @Test
void testCupoDisponible_SeccionNoExiste() throws Exception {

    // Simula que la sección no se encuentra
    when(seccionService.obtenerPorId(99L)).thenReturn(null);

    mockMvc.perform(get("/api/secciones/99/cupo-disponible"))
            .andExpect(status().isNotFound());
}






    // 6. ACTUALIZAR CUPOS DISPONIBLES


@Test
void testActualizarCupoDisponible_SeccionExiste() throws Exception {
    Seccion seccion = new Seccion();
    seccion.setId(1L);
    seccion.setCupoDisponible(15);

    when(seccionService.actualizarCupoDisponible(1L, 15)).thenReturn(seccion);

    mockMvc.perform(put("/api/secciones/1/actualizar-cupo?nuevoCupo=15"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cupoDisponible").value(15));
}




@Test
void testActualizarCupoDisponible_SeccionNoExiste() throws Exception {
    when(seccionService.actualizarCupoDisponible(1L, 15)).thenReturn(null);

    mockMvc.perform(put("/api/secciones/1/actualizar-cupo")
            .param("nuevoCupo", "15"))
            .andExpect(status().isNotFound());
}




// 7. ACTUALIZAR SECCIÓN


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
