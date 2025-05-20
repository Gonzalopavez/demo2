package com.example.demo2.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Seccion {

     private int id;
    private int idCurso;
    private int idDocente;
    private String nombre;
    private int cupoMaximo;
    private int cupoDisponible;
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
    