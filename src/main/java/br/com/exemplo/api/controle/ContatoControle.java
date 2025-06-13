package br.com.exemplo.api.controle;

import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo.api.modelo.ContatoModelo;
import br.com.exemplo.api.modelo.RespostaModelo;
import br.com.exemplo.api.servico.ContatoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class ContatoControle {
    
    // Serviço de processamento e validações
    @Autowired
    private ContatoServico contatoServico;

    // Testar se a API está rodando corretamente
    @GetMapping("")
    public String apiFuncionando(){
        return "API funcionando.";
    }

    // Obter uma lista com todos contatos registrados
    @GetMapping("/listar")
    public Iterable<ContatoModelo> listar(){
        return contatoServico.listar();
    }
    
    // Cadastrar um novo contato
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ContatoModelo contatoModelo){
        return contatoServico.cadastrarAlterar(contatoModelo,"cadastrar");
    }

    // Alterar um contato existente
    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody ContatoModelo contatoModelo){
        return contatoServico.cadastrarAlterar(contatoModelo,"alterar");
    }

    // Remover um contato
    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return contatoServico.remover(codigo);
    }

}
