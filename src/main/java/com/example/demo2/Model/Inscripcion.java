package com.example.demo2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idEstudiante;
    private int idCurso;

    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;

    @OneToOne(cascade = CascadeType.ALL)
    private EstadoInscripcion estado;






    
    // Métodos para cambiar el estado de la inscripción
    public void activar() {
        this.estado.setNombreEstado(EstadoInscripcionEnum.ACTIVA);
        this.estado.setFechaCambio(new Date());
    }

    public void completar() {
        this.estado.setNombreEstado(EstadoInscripcionEnum.COMPLETA);
        this.estado.setFechaCambio(new Date());
    }

    public void cancelar() {
        this.estado.setNombreEstado(EstadoInscripcionEnum.CANCELADA);
        this.estado.setFechaCambio(new Date());
    }

    public void cambiarEstado(EstadoInscripcionEnum nuevoEstado) {
        this.estado.setNombreEstado(nuevoEstado);
        this.estado.setFechaCambio(new Date());
    }

    public EstadoInscripcionEnum obtenerEstado() {
        return this.estado.getNombreEstado();
    }

    @Override
    public String toString() {
        return "Inscripcion [id=" + id + ", idEstudiante=" + idEstudiante + ", idCurso=" + idCurso
                + ", fechaInscripcion=" + fechaInscripcion + ", estado=" + estado + "]";
    }
}

    