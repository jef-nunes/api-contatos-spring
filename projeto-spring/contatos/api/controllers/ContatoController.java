package com.example.contatos.api.controllers;

import com.example.contatos.api.dtos.ContatoRequestDto;
import com.example.contatos.api.dtos.ContatoResponseDto;
import com.example.contatos.domain.mappers.ContatoMapper;
import com.example.contatos.domain.models.entities.Contato;
import com.example.contatos.services.ContatoService;
import com.example.contatos.api.controllers.util.ResponseBuilder;
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
        if(entity!=null) {
            ContatoResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        // Se o erro 404 não for lançado pelo GlobalExceptionHandler
        // então ocorreu algum outro erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }

    // GET - Listar todos os contatos
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Contato> entityList = service.findAll();
        List<ContatoResponseDto> responseDtoList = new ArrayList<>();
        for (Contato entity : entityList) {
            responseDtoList.add(contatoMapper.toResponseDto(entity));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Encontrar contato por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Contato entity = service.find(id);
        if(entity!=null) {
            ContatoResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        // Erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }

    // PUT - Atualizar contato
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ContatoRequestDto requestDTO) {
        Contato entity = service.update(id, requestDTO);
        if(entity!=null) {
            ContatoResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        // Erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }

    // DELETE - Remover contato
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }

    // POST - Adicionar tag
    @PostMapping(value = "/{contatoId}/relacionamentos/tags/{tagId}")
    public ResponseEntity<?> addTag(@PathVariable Long contatoId, @PathVariable Long tagId) {
        Contato entity = service.addTag(contatoId, tagId);
        if(entity!=null) {
            ContatoResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));

        }
        // Erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }

    // DELETE - Remover tag
    @DeleteMapping(value = "/{contatoId}/relacionamentos/tags/{tagId}")
    public ResponseEntity<?> removeTag(@PathVariable Long contatoId, @PathVariable Long tagId) {
        Contato entity = service.removeTag(contatoId, tagId);
        if(entity!=null) {
            ContatoResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        // Erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }
}
