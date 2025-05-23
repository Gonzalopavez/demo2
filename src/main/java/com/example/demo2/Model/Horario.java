package com.example.demo2.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor //NECESARIO PARA JPA


public class Horario {
    //GENERA EL VALOR DEL ID AUTOMATICAMENTE
    //EJEMPLO: SI HAY 3 HORARIOS EN LA BASE DE DATOS, EL SIGUIENTE HORARIO QUE SE AGREGA TENDRA ID=4

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    //ATRIBUTOS BASICOS DEL HORARIO
    private int id;
    private String dia;        
    private String horaInicio; 
    private String horaFin;    
}
