package com.example.demo2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoInscripcion {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EstadoInscripcionEnum nombreEstado;

    @Temporal(TemporalType.DATE)
    private Date fechaCambio;

    public boolean verDisponibilidad() {

        
        //solo las activas estarán disponibles
        return this.nombreEstado == EstadoInscripcionEnum.ACTIVA;
    }
}
