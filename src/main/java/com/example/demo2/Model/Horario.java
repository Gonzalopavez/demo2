package com.example.demo2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dia;        // Ej: "Lunes"
    private String horaInicio; // Ej: "08:00"
    private String horaFin;    // Ej: "10:00"
}
