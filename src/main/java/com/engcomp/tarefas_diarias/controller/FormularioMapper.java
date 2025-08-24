package com.engcomp.tarefas_diarias.controller;

import com.engcomp.tarefas_diarias.controller.dto.FormularioDTO;
import com.engcomp.tarefas_diarias.entity.Formulario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FormularioMapper {

    @Mapping(source = "data", target = "data")
    @Mapping(source = "veiculo", target = "veiculo")
    @Mapping(source = "projeto", target = "projeto")
    @Mapping(source = "inicio", target = "inicio")
    @Mapping(source = "fim", target = "fim")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "operador" ,target = "operador")
    Formulario toEntity(FormularioDTO dto);
    FormularioDTO toDTO(Formulario formulario);
}
