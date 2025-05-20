package com.example.demo2.Model;

import java.util.Date;

public class Inscripcion {
    private int id;
    private int idEstudiante;
    private int idCurso;
    private Date fechaInscripcion;
    private EstadoInscripcionEnum estado;

    // constructor con parámetros

    public Inscripcion(int id, int idEstudiante, int idCurso, Date fechaInscripcion, EstadoInscripcionEnum estado) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    

    //aqui abajo los getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public EstadoInscripcionEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcionEnum estado) {
        this.estado = estado;
    }





    // Métodos para cambiar el estado de la inscripción


    
    public void activar() {
        this.estado = EstadoInscripcionEnum.ACTIVA;
    }

    public void completar() {
        this.estado = EstadoInscripcionEnum.COMPLETA;
    }

    public void cancelar() {
        this.estado = EstadoInscripcionEnum.CANCELADA;
    }

    public void cambiarEstado(EstadoInscripcionEnum nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public EstadoInscripcionEnum obtenerEstado() {
        return this.estado;
    }

    @Override
    public String toString() {
        return "Inscripcion [id=" + id + ", idEstudiante=" + idEstudiante + ", idCurso=" + idCurso
                + ", fechaInscripcion=" + fechaInscripcion + ", estado=" + estado + "]";
    }
}
