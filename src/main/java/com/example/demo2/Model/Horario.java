package com.example.demo2.Model;


// permite conectar con la base de datos
import jakarta.persistence.*;

//librerias de lombok para generar getters y setters automaticamente
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Indicamos que esta clase es una entidad JPA, lo que significa que se corresponde con una tabla en la base de datos
@Entity

//Anotaciones de Lombok para generar automaticamente los getters, setters, constructores y toString
@Data
@AllArgsConstructor
@NoArgsConstructor 


public class Horario {

    
    //GENERA EL VALOR DEL ID AUTOMATICAMENTE
    //EJEMPLO: SI HAY 3 HORARIOS EN LA BASE DE DATOS, EL SIGUIENTE HORARIO QUE SE AGREGA TENDRA ID=4
    
    @Id //Indica que este atributo es la clave primaria de la entidad Horario
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    //ATRIBUTOS BASICOS DEL HORARIO
    private int id;
    private String dia;        
    private String horaInicio; 
    private String horaFin;    
}
