package com.example.contatos.domain.mappers;

import com.example.contatos.api.dtos.TagRequestDto;
import com.example.contatos.api.dtos.TagResponseDto;
import com.example.contatos.domain.models.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper{

    @Mapping(target="id",ignore = true)
    Tag toEntity(TagRequestDto requestDto);

    TagResponseDto toResponseDto(Tag entity);
}
