package com.example.contatos.dtos;

import lombok.*;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponseDto {
    private Long id;
    private String nome;
    private String descricao;
    private List<Long> listaContatoId;
}
