package br.com.exemplo.api.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.exemplo.api.modelo.ContatoModelo;

@Repository
public interface ContatoRepositorio extends CrudRepository<ContatoModelo,Long> {
    ContatoModelo findByEmail(String email);
}
