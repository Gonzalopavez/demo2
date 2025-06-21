


package com.example.demo2.Model;
import jakarta.persistence.*; // permite conectar con la base de datos

//librerias de lombok para generar getters y setters automaticamente
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//maneja listas de objetos, en este caso de horarios
import java.util.ArrayList;
import java.util.List;



@Entity //representa una tabla en la base de datos
@Data
@AllArgsConstructor
@NoArgsConstructor




public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Es requerido para que JPA pueda identificar la entidad de forma única
    //El ID es la clave primaria de la tabla Seccion, se genera automaticamente al insertar un nuevo registro

    //ATRIBUTOS BASICOS DE LA SECCION

    private int idCurso;
    private int idDocente;
    private String nombre;
    private int cupoMaximo;
    private int cupoDisponible;



    //una seccion puede tener muchos horarios, y un horario pertenece a una sola seccion
    //cascade = CascadeType.ALL significa que si se elimina la seccion, se eliminan los horarios asociados
    //fetch = FetchType.EAGER significa que se cargan los horarios asociados a la seccion al momento de cargar la seccion


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Horario> horarios = new ArrayList<>();


    // SIRVE PARA ACTUALIZAR LOS CUPOS DISPONIBLES DE UNA SECCION

    public void actualizarCupo(int cupoNuevo) {
        this.cupoDisponible = cupoNuevo;
    }

    // SIRVE PARA VER SI HAY CUPOS DISPONIBLES

    public boolean esCupoDisponible() {
        return cupoDisponible > 0;
    }

    //SIRVE PARA AGREGAR HORARIOS A LA SECCION (AGREGA UN NUEVO OBJETO A LA LISTA DE HORARIOS DE LA SECCION)

    public void agregarHorario(Horario horario) {
        this.horarios.add(horario);
    }
}
