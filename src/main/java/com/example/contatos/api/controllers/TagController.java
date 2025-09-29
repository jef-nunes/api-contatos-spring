package com.example.contatos.api.controllers;

import com.example.contatos.api.dtos.TagRequestDto;
import com.example.contatos.api.dtos.TagResponseDto;
import com.example.contatos.domain.mappers.TagMapper;
import com.example.contatos.domain.models.entities.Tag;
import com.example.contatos.services.TagService;
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
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController{

    private final TagService service;
    private final TagMapper contatoMapper;

    // POST - Criar contato
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody TagRequestDto requestDTO) {
        Tag entity = service.create(requestDTO);
        if(entity!=null) {
            TagResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        // Se o erro 404 não for lançado pelo GlobalExceptionHandler
        // então ocorreu algum outro erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));

    }

    // GET - Todos os contatos
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        List<Tag> entityList = service.findAll();
        List<TagResponseDto> responseDtoList = new ArrayList<>();
        for (Tag entity : entityList) {
            responseDtoList.add(contatoMapper.toResponseDto(entity));
        }
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(responseDtoList));
    }

    // GET - Tag por ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Tag entity = service.find(id);
        if(entity!=null) {
            TagResponseDto responseDto = contatoMapper.toResponseDto(entity);
            return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBuilder.buildFailureResponse(List.of("Erro interno do servidor.")));
    }

    // PUT - Atualizar contato
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TagRequestDto requestDTO) {
        Tag entity = service.update(id, requestDTO);
        TagResponseDto responseDto = contatoMapper.toResponseDto(entity);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(List.of(responseDto)));
    }

    // DELETE - Remover contato
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ResponseBuilder.buildSuccessResponse(new ArrayList<>()));
    }
    
}
