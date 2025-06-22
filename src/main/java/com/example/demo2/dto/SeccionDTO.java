package com.example.demo2.dto;


import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.example.demo2.Model.Horario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeccionDTO extends RepresentationModel<SeccionDTO> {
    private Long id;
    private int idCurso;
    private int idDocente;
    private String nombre;
    private int cupoMaximo;
    private int cupoDisponible;
    private List<Horario> horarios;
}
