package com.example.demo2.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets") // Aquí se especifica el nombre de la tabla
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoTicketEnum estado;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    private Date fechaResolucion;

    @Enumerated(EnumType.STRING)
    private PrioridadEnum prioridad;

    @Enumerated(EnumType.STRING)
    private TipoSoporteEnum tipoSoporte;

    private int idSolicitante;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoTicketEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicketEnum estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public PrioridadEnum getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadEnum prioridad) {
        this.prioridad = prioridad;
    }

    public TipoSoporteEnum getTipoSoporte() {
        return tipoSoporte;
    }

    public void setTipoSoporte(TipoSoporteEnum tipoSoporte) {
        this.tipoSoporte = tipoSoporte;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }
}
