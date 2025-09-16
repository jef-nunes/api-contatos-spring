package com.example.contatos.mapstruct;

import com.example.contatos.dtos.TagRequestDto;
import com.example.contatos.dtos.TagResponseDto;
import com.example.contatos.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper{

    @Mapping(target="id",ignore = true)
    Tag toEntity(TagRequestDto requestDto);

    TagResponseDto toResponseDto(Tag entity);
}
