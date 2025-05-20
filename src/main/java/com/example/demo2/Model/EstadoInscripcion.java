package com.example.demo2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class EstadoInscripcion {

    // Atributos
    private UUID id;
    private EstadoInscripcionEnum nombreEstado;
    private Date fechaCambio;

    public boolean verDisponibilidad() {
        return nombreEstado == EstadoInscripcionEnum.ACTIVA;
    }
}
