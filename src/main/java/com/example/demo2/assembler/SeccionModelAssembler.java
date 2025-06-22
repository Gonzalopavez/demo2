package com.example.demo2.assembler;

import com.example.demo2.dto.SeccionDTO;
import com.example.demo2.Model.Seccion;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.example.demo2.Controller.SeccionController;

@Component
public class SeccionModelAssembler implements RepresentationModelAssembler<Seccion, SeccionDTO> {

    @Override
    public SeccionDTO toModel(Seccion seccion) {
        SeccionDTO dto = new SeccionDTO();
        dto.setId(seccion.getId());
        dto.setIdCurso(seccion.getIdCurso());
        dto.setIdDocente(seccion.getIdDocente());
        dto.setNombre(seccion.getNombre());
        dto.setCupoMaximo(seccion.getCupoMaximo());
        dto.setCupoDisponible(seccion.getCupoDisponible());
        dto.setHorarios(seccion.getHorarios());

        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeccionController.class).obtenerSeccionPorId(seccion.getId()))
                .withSelfRel());

        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeccionController.class).listarSecciones())
                .withRel("todas"));

        return dto;
    }
}
