package com.example.estagio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.estagio.domain.Documents;
import com.example.estagio.exceptions.ResourceNotFoundException;
import com.example.estagio.repository.DocumentRepository;

@Service
public class DocumentsService {

    @Autowired
    private DocumentRepository documentRepository;

    public Page<Documents> findAll(Pageable pageable){
        Page<Documents> documents = documentRepository.findAll(pageable);
        return documents;
    }
    public Documents findById(String id){
        try{
        Optional<Documents> obj = documentRepository.findById(id);
        return obj.get();
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Documents insert(Documents obj) {
        return documentRepository.save(obj);
    }

    public void delete(String id) {
        findById(id);
        documentRepository.deleteById(id);
    }
}
