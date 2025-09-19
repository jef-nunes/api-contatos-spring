package com.example.contatos.services;

import com.example.contatos.api.dtos.TagRequestDto;
import com.example.contatos.exceptions.ResourceNotFoundException;
import com.example.contatos.domain.mappers.TagMapper;
import com.example.contatos.domain.models.entities.Tag;
import com.example.contatos.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(TagService.class.getName());

    // Mapper
    private final TagMapper tagMapper;

    // Repositório JPA

    // Tag
    private final TagRepository tagRepository;

    /*
     *       CRUD
     */

    // Listar
    public List<Tag> findAll(){
        logger.info("Finding all Tags.");
        return tagRepository.findAll();
    }

    // Encontrar
    public Tag find(Long id){
        logger.info("Finding the Tag with ID="+id+".");
        return tagRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
    }

    // Criar
    public Tag create(TagRequestDto requestDto){
        Tag entity = tagMapper.toEntity(requestDto);
        return  tagRepository.save(entity);
    }

    // Atualizar
    public Tag update(Long id, TagRequestDto requestDto){
        Tag oldTag = tagRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
        if(oldTag!=null) {
            Tag updatedTag = tagMapper.toEntity(requestDto);
            updatedTag.setId(oldTag.getId());
            return tagRepository.save(updatedTag);
        }
        else{
            return null;
        }
    }

    // Remover
    public void delete(Long id){
        tagRepository.findById(id).orElseThrow(
                ResourceNotFoundException::new
        );
        tagRepository.deleteById(id);
    }

}