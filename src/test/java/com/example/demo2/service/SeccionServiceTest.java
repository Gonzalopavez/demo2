package com.example.demo2.service;

import java.util.Optional; // para trabajr con posibles valores nulos
import com.example.demo2.Model.Seccion;
import com.example.demo2.repository.SeccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class SeccionServiceTest {

    @Mock
    private SeccionRepository seccionRepository; //se simula el repositorio SeccionRepository, no se usa la base de datos real

    @InjectMocks
    private SeccionService seccionService;

    //antes de cada test, se crea una instancia de SeccionService y se inyecta el mock del repositorio
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }






    // 1. Test para guardar una sección

    @Test
    void testGuardarSeccion() {

        //se crea una sección nueva sin ID (null) y se simula que el repositorio la guarda y retorna una sección con ID 1
        Seccion seccion = new Seccion(null, 1, 10, "Sección A", 30, 30, new ArrayList<>());
        Seccion seccionGuardada = new Seccion(1L, 1, 10, "Sección A", 30, 30, new ArrayList<>());
     

        // al hacer save, se retorna la sección guardada con ID 1
        when(seccionRepository.save(seccion)).thenReturn(seccionGuardada);


        // se llama al método guardarSeccion del servicio
        Seccion resultado = seccionService.guardarSeccion(seccion);
         
        // se verifica que el resultado no sea nulo y que tenga el ID y nombre esperados y que se guardó correctamente
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Sección A");
        verify(seccionRepository).save(seccion);
    }





    // 2. Test para listar todas las secciones

    @Test
    void testListarSecciones() {

        //2 secciones simuladas
        Seccion s1 = new Seccion(1L, 1, 10, "Sección A", 30, 25, new ArrayList<>());
        Seccion s2 = new Seccion(2L, 2, 11, "Sección B", 40, 35, new ArrayList<>());
        
        //se simula que el repositorio retorna una lista con las 2 secciones
        List<Seccion> listaMock = List.of(s1, s2);
        when(seccionRepository.findAll()).thenReturn(listaMock);
        
        //se valida que el servicio retorna la lista esperada y se conrfirma que se llamó al repositorio
        List<Seccion> resultado = seccionService.obtenerTodas();
        assertThat(resultado).hasSize(2).contains(s1, s2);
        verify(seccionRepository).findAll();
    }





    




    // 3. Test para obtener una sección por ID

@Test
void testObtenerSeccionPorId() {
    
    Seccion seccion = new Seccion(1L, 1, 10, "Sección A", 30, 25, new ArrayList<>());
    
    
    // Simula que el repositorio encuentra la sección con ID 1
    when(seccionRepository.findById(1L)).thenReturn(Optional.of(seccion));

    Seccion resultado = seccionService.obtenerPorId(1L);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getId()).isEqualTo(1L);
    assertThat(resultado.getNombre()).isEqualTo("Sección A");
    verify(seccionRepository).findById(1L);
}



// 4. Test para eliminar una sección por su ID

@Test
void testEliminarSeccion() {
    Long id = 1L;

    
    seccionService.eliminarSeccion(id);

    verify(seccionRepository).deleteById(id);
    //se verifica que efectivamente se llamó al método deleteById del repositorio con el ID correcto
}


// 5. Test para verificar si hay cupo disponible en una sección

@Test
void testHayCupoDisponible_true() {
    Seccion seccion = new Seccion();
    seccion.setCupoDisponible(5);

    when(seccionRepository.findById(1L)).thenReturn(Optional.of(seccion));

    boolean resultado = seccionService.hayCupoDisponible(1L);

    assertTrue(resultado);
}

@Test
void testHayCupoDisponible_false() {
    Seccion seccion = new Seccion();
    seccion.setCupoDisponible(0);

    when(seccionRepository.findById(1L)).thenReturn(Optional.of(seccion));

    boolean resultado = seccionService.hayCupoDisponible(1L);

    assertFalse(resultado);
}


// 6. Test para actualizar el cupo disponible de una sección

@Test
void testActualizarCupoDisponible_existente() {
    Seccion seccion = new Seccion();
    seccion.setId(1L);
    seccion.setCupoDisponible(5);

    when(seccionRepository.findById(1L)).thenReturn(Optional.of(seccion));
    when(seccionRepository.save(any(Seccion.class))).thenReturn(seccion);

    Seccion resultado = seccionService.actualizarCupoDisponible(1L, 10);

    assertNotNull(resultado);
    assertEquals(10, resultado.getCupoDisponible());
}

@Test
void testActualizarCupoDisponible_noEncontrada() {
    when(seccionRepository.findById(1L)).thenReturn(Optional.empty());

    Seccion resultado = seccionService.actualizarCupoDisponible(1L, 10);

    assertNull(resultado);
}




















// 7. test para actulizar una seccion
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




