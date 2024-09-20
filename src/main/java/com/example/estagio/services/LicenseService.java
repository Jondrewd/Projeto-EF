package com.example.estagio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.estagio.domain.License;
import com.example.estagio.exceptions.ResourceNotFoundException;
import com.example.estagio.repository.LicenseRepository;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    public Page<License> findAll(Pageable pageable){
        Page<License> licenses = licenseRepository.findAll(pageable);
        return licenses;
    }
    public License findById(String id){
        try{
        Optional<License> obj = licenseRepository.findById(id);
        return obj.get();
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public License insert(License obj) {
        return licenseRepository.save(obj);
    }

    public void delete(String id) {
        findById(id);
        licenseRepository.deleteById(id);
    }
}
