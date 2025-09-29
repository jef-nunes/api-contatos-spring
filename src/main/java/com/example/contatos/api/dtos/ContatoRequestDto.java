package com.example.contatos.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatoRequestDto {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 1, max = 255, message = "O nome deve ter entre 1 e 255 caracteres")
    private String nome;

    @Email(message = "O email informado é inválido")
    @Size(max = 255, message = "O email não pode ter mais de 255 caracteres")
    private String email;

    @Size(max = 255, message = "A descrição não pode ter mais de 255 caracteres")
    private String descricao;

    @Size(max = 20, message = "O telefone não pode ter mais de 20 caracteres")
    private String telefone;
}
