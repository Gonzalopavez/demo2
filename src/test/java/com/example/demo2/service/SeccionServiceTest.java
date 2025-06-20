package com.example.demo2.service;

import java.util.Optional;
import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class SeccionServiceTest {

    @Mock
    private SeccionRepository seccionRepository;

    @InjectMocks
    private SeccionService seccionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }






    // Test para guardar una sección

    @Test
    void testGuardarSeccion() {
        Seccion seccion = new Seccion(null, 1, 10, "Sección A", 30, 30, new ArrayList<>());
        Seccion seccionGuardada = new Seccion(1L, 1, 10, "Sección A", 30, 30, new ArrayList<>());

        when(seccionRepository.save(seccion)).thenReturn(seccionGuardada);

        Seccion resultado = seccionService.guardarSeccion(seccion);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Sección A");
        verify(seccionRepository).save(seccion);
    }





    // Test para listar todas las secciones

    @Test
    void testListarSecciones() {
        Seccion s1 = new Seccion(1L, 1, 10, "Sección A", 30, 25, new ArrayList<>());
        Seccion s2 = new Seccion(2L, 2, 11, "Sección B", 40, 35, new ArrayList<>());
        List<Seccion> listaMock = List.of(s1, s2);

        when(seccionRepository.findAll()).thenReturn(listaMock);

        List<Seccion> resultado = seccionService.obtenerTodas();

        assertThat(resultado).hasSize(2).contains(s1, s2);
        verify(seccionRepository).findAll();
    }




    // Test para obtener una sección por ID

@Test
void testObtenerSeccionPorId() {
    Seccion seccion = new Seccion(1L, 1, 10, "Sección A", 30, 25, new ArrayList<>());

    when(seccionRepository.findById(1L)).thenReturn(Optional.of(seccion));

    Seccion resultado = seccionService.obtenerPorId(1L);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1L);
    assertThat(resultado.getNombre()).isEqualTo("Sección A");
    verify(seccionRepository).findById(1L);
}



// Test para eliminar una sección por su ID

@Test
void testEliminarSeccion() {
    Long id = 1L;

    // No se necesita when(...) porque deleteById no retorna nada
    seccionService.eliminarSeccion(id);

    verify(seccionRepository).deleteById(id);
}


//test para actulizar una seccion
@Test
public void testActualizarSeccion() {

    // Sección original existente

    Seccion existente = new Seccion();
    existente.setId(1L);
    existente.setNombre("Sección A");
    existente.setIdCurso(101);
    existente.setIdDocente(201);
    existente.setCupoMaximo(30);
    existente.setCupoDisponible(10);

    // Sección nueva con cambios

    Seccion modificada = new Seccion();
    modificada.setNombre("Sección B");
    modificada.setIdCurso(102);
    modificada.setIdDocente(202);
    modificada.setCupoMaximo(40);
    modificada.setCupoDisponible(15);




    // Simula que el repositorio encuentra la sección existente
    when(seccionRepository.findById(1L)).thenReturn(Optional.of(existente));



    // Simula que el repositorio guarda la sección modificada
    when(seccionRepository.save(any(Seccion.class))).thenAnswer(i -> i.getArgument(0));



    // Ejecutar
    Seccion actualizada = seccionService.actualizarSeccion(1L, modificada);


    // Verificaciones
    assertNotNull(actualizada);
    assertEquals("Sección B", actualizada.getNombre());
    assertEquals(102, actualizada.getIdCurso());
    assertEquals(202, actualizada.getIdDocente());
    assertEquals(40, actualizada.getCupoMaximo());
    assertEquals(15, actualizada.getCupoDisponible());
}
}




