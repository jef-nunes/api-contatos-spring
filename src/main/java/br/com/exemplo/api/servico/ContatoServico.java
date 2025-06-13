package br.com.exemplo.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.exemplo.api.modelo.ContatoModelo;
import br.com.exemplo.api.modelo.RespostaModelo;
import br.com.exemplo.api.repositorio.ContatoRepositorio;

@Service
public class ContatoServico {
    @Autowired
    private ContatoRepositorio contatoRepositorio;

    @Autowired
    private RespostaModelo respostaModelo;

    // Cadastrar ou alterar um contato
    public ResponseEntity<?> cadastrarAlterar(ContatoModelo contatoModelo, String acao){
        
        // Validações
        
        // Valida nome
        // Nome do contato não pode ser vazio
        if(contatoModelo.getNome().equals("")){
            respostaModelo.setMensagem("O nome do contato é obrigatório.");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }


        // Valida telefone
        // Telefone do contato não pode ser vazio
        if(contatoModelo.getTelefone().equals("")){
            respostaModelo.setMensagem("O telefone do contato é obrigatório.");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }
        // O telefone deve seguir padrão
        else if(!contatoModelo.getTelefone().matches("^\\(?\\d{2,3}\\)?\\s?\\d{5}-?\\d{4}$")){
            respostaModelo.setMensagem("O telefone deve seguir o padrão (ddd) xxxxx-xxxx.");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
        }


        // Valida email
        // Se um email tiver sido passado
        if(contatoModelo.getEmail()!=null){
            // O email deve ser único
            if(contatoRepositorio.findByEmail(contatoModelo.getEmail())!=null){
                respostaModelo.setMensagem("Um contato com este email já existe.");
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
            // O email deve seguir padrão
            else if(!contatoModelo.getEmail().contains("@") || !contatoModelo.getEmail().contains(".")){
                respostaModelo.setMensagem("O email fornecido é inválido.");
                return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.BAD_REQUEST);
            }
        }


        // Em caso de tudo ser válido
        // retornar com base no argumento "acao"
        // Cadastrar 
        if(acao.equals("cadastrar")){
            return new ResponseEntity<ContatoModelo>(contatoRepositorio.save(contatoModelo),HttpStatus.CREATED);
        }
        // Alterar
        else if(acao.equals("alterar")){
            return new ResponseEntity<ContatoModelo>(contatoRepositorio.save(contatoModelo),HttpStatus.OK);
        }
        // Erro interno
        else{
            respostaModelo.setMensagem("Ocorreu um erro, tente novamente.");
            return new ResponseEntity<RespostaModelo>(respostaModelo,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remover um contato
    public ResponseEntity<RespostaModelo> remover(long codigo){
        contatoRepositorio.deleteById(codigo);
        respostaModelo.setMensagem("O contato foi removido com sucesso.");
        return new ResponseEntity<RespostaModelo>(respostaModelo, HttpStatus.OK);
    }


    // Listar contatos registrados
    public Iterable<ContatoModelo> listar(){
        return contatoRepositorio.findAll();
    }

}
