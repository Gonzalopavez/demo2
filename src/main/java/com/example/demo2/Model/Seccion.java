package com.example.demo2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idCurso;
    private int idDocente;
    private String nombre;
    private int cupoMaximo;
    private int cupoDisponible;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Horario> horarios = new ArrayList<>();

    public void actualizarCupo(int cupoNuevo) {
        this.cupoDisponible = cupoNuevo;
    }

    public boolean esCupoDisponible() {
        return cupoDisponible > 0;
    }

    public void agregarHorario(Horario horario) {
        this.horarios.add(horario);
    }
}
 