package com.example.contatos.mapstruct;

import com.example.contatos.dtos.ContatoRequestDto;
import com.example.contatos.dtos.ContatoResponseDto;
import com.example.contatos.models.Contato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContatoMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target="dataCriado",ignore = true)
    @Mapping(target="dataModificado",ignore = true)
    Contato toEntity(ContatoRequestDto requestDto);

    ContatoResponseDto toResponseDto(Contato entity);
}
