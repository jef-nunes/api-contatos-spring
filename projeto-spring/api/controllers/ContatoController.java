package com.example.contatos.controllers;

import com.example.contatos.dtos.ContatoRequestDto;
import com.example.contatos.dtos.ContatoResponseDto;
import com.example.contatos.mapstruct.ContatoMapper;
import com.example.contatos.models.Contato;
import com.example.contatos.services.ContatoService;
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
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService service;
    private final ContatoMapper contatoMapper;

    // POST - Criar contato
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody ContatoRequestDto requestDTO) {
        Contato entity = service.create(requestDTO);
        ContatoResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.buildResponse(dto));
    }

    // GET - Todos os contatos
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Contato> entityList = service.findAll();
        List<ContatoResponseDto> dtoList = new ArrayList<>();
        for (Contato entity : entityList) {
            dtoList.add(contatoMapper.toResponseDto(entity));
        }
        return ResponseEntity.ok(ResponseBuilder.buildResponse(dtoList));
    }

    // GET - Contato por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Contato entity = service.find(id);
        ContatoResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildResponse(dto));
    }

    // PUT - Atualizar contato
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ContatoRequestDto requestDTO) {
        Contato entity = service.update(id, requestDTO);
        ContatoResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildResponse(dto));
    }

    // DELETE - Remover contato
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.buildEmptyDataResponse());
    }

    // POST - Adicionar tag
    @PostMapping(value = "/{contatoId}/tags/{tagId}")
    public ResponseEntity<?> addTag(@PathVariable Long contatoId, @PathVariable Long tagId) {
        Contato entity = service.addTag(contatoId, tagId);
        ContatoResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildResponse(dto));
    }

    // DELETE - Remover tag
    @DeleteMapping(value = "/{contatoId}/tags/{tagId}")
    public ResponseEntity<?> removeTag(@PathVariable Long contatoId, @PathVariable Long tagId) {
        Contato entity = service.removeTag(contatoId, tagId);
        ContatoResponseDto dto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildResponse(dto));
    }
}
