package com.example.demo2.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Horario {  

     private int id;
    private DiaSemanaEnum dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    
}
