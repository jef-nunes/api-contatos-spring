package com.example.contatos.api.dtos;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoResponseDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String descricao;
    private LocalDateTime dataCriado;
    private LocalDateTime dataModificado;
    private List<Long> listaTagId;
}