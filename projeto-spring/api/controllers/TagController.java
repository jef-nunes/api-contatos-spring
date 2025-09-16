package com.example.contatos.controllers;

import com.example.contatos.dtos.TagRequestDto;
import com.example.contatos.dtos.TagResponseDto;
import com.example.contatos.mapstruct.TagMapper;
import com.example.contatos.models.Tag;
import com.example.contatos.services.TagService;
import com.example.contatos.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController{

    private final TagService service;
    private final TagMapper contatoMapper;

    // POST - Criar contato
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody TagRequestDto requestDTO) {
        Tag entity = service.create(requestDTO);
        TagResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.buildSuccessResponse(dto));
    }

    // GET - Todos os contatos
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Tag> entityList = service.findAll();
        List<TagResponseDto> dtoList = new ArrayList<>();
        for (Tag entity : entityList) {
            dtoList.add(contatoMapper.toResponseDto(entity));
        }
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(dtoList));
    }

    // GET - Tag por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Tag entity = service.find(id);
        TagResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(dto));
    }

    // PUT - Atualizar contato
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TagRequestDto requestDTO) {
        Tag entity = service.update(id, requestDTO);
        TagResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(dto));
    }

    // DELETE - Remover contato
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessEmptyDataResponse());
    }
    
}
