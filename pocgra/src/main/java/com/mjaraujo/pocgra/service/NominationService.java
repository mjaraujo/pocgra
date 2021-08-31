package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.entity.Nomination;
import com.mjaraujo.pocgra.repository.INominationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class NominationService {

    @Autowired
    private INominationRepository nominationRepository;

    public Nomination save(Nomination nomination) {
        return nominationRepository.save(nomination);
    }

    public List<Nomination> findAll() {
        return nominationRepository.findAll();
    }

    public Optional<Nomination> findById(Long id) {
        return nominationRepository.findById(id);
    }

    public void delete(Nomination nomination) {
        nominationRepository.delete(nomination);
    }
}
