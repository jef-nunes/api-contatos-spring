package com.example.contatos.repositories;

import com.example.contatos.domain.models.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
