package com.example.contatos.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagRequestDto {

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 255, message = "O nome deve ter até 255 caracteres.")
    private String nome;

    @Size(max = 255, message = "A descrição deve ter até 255 caracteres.")
    private String descricao;

}
