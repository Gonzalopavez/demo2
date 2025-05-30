package com.example.demo2.Model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets") // nombra explicitamente la tabla, si no se especifica, se usará el nombre de la clase en minúsculas


public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID único automáticamente
    private int id;

    //Campos de texto que especifican el problema del ticket
    
    private String titulo;
    private String descripcion;

    // le dice a jpa que este campo es un enumerado y se almacenará como una cadena en la base de datos
    @Enumerated(EnumType.STRING)
    private EstadoTicketEnum estado;


    // Campos de fecha para registrar la creación y resolución del ticket

    @Temporal(TemporalType.DATE)//le dice a jpa que solo quieres almacenar la fecha, no la hora
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)//le dice a jpa que solo quieres almacenar la fecha, no la hora
    private Date fechaResolucion;


    

    @Enumerated(EnumType.STRING)
    private PrioridadEnum prioridad;

    @Enumerated(EnumType.STRING)
    private TipoSoporteEnum tipoSoporte;


    // Campo para almacenar el ID del solicitante del ticket, no es un objeto relacionado, solo un ID

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
