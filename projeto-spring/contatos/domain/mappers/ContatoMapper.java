package com.example.contatos.domain.mappers;

import com.example.contatos.api.dtos.ContatoRequestDto;
import com.example.contatos.api.dtos.ContatoResponseDto;
import com.example.contatos.domain.models.entities.Contato;
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
