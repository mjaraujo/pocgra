package com.mjaraujo.pocgra.service;


import com.mjaraujo.pocgra.entity.Producer;
import com.mjaraujo.pocgra.repository.IProducerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProducerService {

    @Autowired
    private IProducerRepository producerRepository;

    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    public void delete(Producer producer) {
        producerRepository.delete(producer);
    }
}
