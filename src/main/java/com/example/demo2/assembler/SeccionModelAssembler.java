package com.example.demo2.assembler;

import com.example.demo2.dto.SeccionDTO;
import com.example.demo2.Model.Seccion;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import com.example.demo2.Controller.SeccionController;

@Component

//Esta clase convierte entidades Seccion a DTOs SeccionDTO con enlaces HATEOAS
//implementando RepresentationModelAssembler, que define el metodo toModel que debo sobrescribir

public class SeccionModelAssembler implements RepresentationModelAssembler<Seccion, SeccionDTO> {


    //metodo recibe una entidad Seccion y devuelve un DTO SeccionDTO con enlaces HATEOAS
    @Override
    public SeccionDTO toModel(Seccion seccion)
    
    
    
    {    //aqui simplemete se copian los atributos de la entidad Seccion al DTO SeccionDTO
        SeccionDTO dto = new SeccionDTO();
        dto.setId(seccion.getId());
        dto.setIdCurso(seccion.getIdCurso());
        dto.setIdDocente(seccion.getIdDocente());
        dto.setNombre(seccion.getNombre());
        dto.setCupoMaximo(seccion.getCupoMaximo());
        dto.setCupoDisponible(seccion.getCupoDisponible());
        dto.setHorarios(seccion.getHorarios());



         //agrega un link a si mismo 

        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeccionController.class).obtenerSeccionPorId(seccion.getId()))
                .withSelfRel());




        //agrega un link a la lista de todas las secciones

        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeccionController.class).listarSecciones())
                .withRel("todas"));

        return dto; //devuelve el DTO con los enlaces HATEOAS añadidos
    }
}
