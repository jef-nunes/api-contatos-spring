package com.example.contatos.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contato implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private String descricao;

    @Column
    private LocalDateTime dataCriado;

    @Column
    private LocalDateTime dataModificado;

    // Relacionamento de muitos para muitos
    @ManyToMany
    @JoinTable(
            name="contato_tag",
            joinColumns = @JoinColumn(name="contato_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    private Set<Tag> tags = new HashSet<>();
}
