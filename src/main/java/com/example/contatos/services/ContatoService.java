package com.example.contatos.services;

import com.example.contatos.api.dtos.ContatoRequestDto;
import com.example.contatos.exceptions.ResourceNotFoundException;
import com.example.contatos.domain.mappers.ContatoMapper;
import com.example.contatos.domain.models.entities.Contato;
import com.example.contatos.domain.models.entities.Tag;
import com.example.contatos.repositories.ContatoRepository;
import com.example.contatos.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(ContatoService.class.getName());

    // Mapper
    private final ContatoMapper contatoMapper;

    // Repositórios JPA

    // Contato
    private final ContatoRepository contatoRepository;

    // Tag
    private final TagRepository tagRepository;

    /*
    *       CRUD
   */

    // Listar
    public List<Contato> findAll(){
        logger.info("Finding all Contatos.");
        return contatoRepository.findAll();
    }

    // Encontrar
    public Contato find(Long id){
        logger.info("Finding the Contato with ID="+id+".");
        return contatoRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
    }

    // Criar
    public Contato create(ContatoRequestDto requestDto){
        logger.info("Creating Contato.");
        Contato entity = contatoMapper.toEntity(requestDto);
        entity.setDataCriado(LocalDateTime.now());
        entity.setDataModificado(LocalDateTime.now());
        return  contatoRepository.save(entity);
    }
    
    // Atualizar
    public Contato update(Long id, ContatoRequestDto requestDto){
        logger.info("Updating the Contato with ID="+id+".");
        Contato oldContato = contatoRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
        if(oldContato!=null) {
            Contato updatedContato = contatoMapper.toEntity(requestDto);
            updatedContato.setId(oldContato.getId());
            // Manter a data de criação
            updatedContato.setDataCriado(oldContato.getDataCriado());
            // Atualizar a data de modificação
            updatedContato.setDataModificado(LocalDateTime.now());
            return contatoRepository.save(updatedContato);
        }
        else{
            return null;
        }
    }

    // Remover
    public void delete(Long id){
        logger.info("Deleting Contato with ID="+id+".");
        contatoRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
        contatoRepository.deleteById(id);
    }


    /*
    *       Relacionamentos
   */

    // Adicionar Tag
    public Contato addTag(Long contatoId, Long tagId){
        logger.info("Adding tag with ID="+tagId+" to Contato with ID="+contatoId+".");
        Contato contato = contatoRepository.findById(contatoId).orElseThrow(
                ResourceNotFoundException::new
        );
        Tag tag = tagRepository.findById(tagId).orElseThrow(
                ResourceNotFoundException::new
        );
        if(contato!=null && tag!=null) {
            contato.getTags().add(tag);
            tag.getContatos().add(contato);
            return contato;
        }
        else{
            return null;
        }
    }

    // Remover Tag
    public Contato removeTag(Long contatoId, Long tagId){
        logger.info("Removing tag with ID="+tagId+" from Contato with ID="+contatoId+".");
        Contato contato = contatoRepository.findById(contatoId).orElseThrow(
                ResourceNotFoundException::new
        );
        Tag tag = tagRepository.findById(tagId).orElseThrow(
                ResourceNotFoundException::new
        );
        if(contato!=null && tag!=null) {
            contato.getTags().remove(tag);
            tag.getContatos().remove(contato);
            return contato;
        }
        else{
            return null;
        }
    }

}
