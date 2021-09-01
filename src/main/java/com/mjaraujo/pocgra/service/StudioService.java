package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.entity.Studio;
import com.mjaraujo.pocgra.repository.IStudioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudioService {
    @Autowired
    private IStudioRepository studioRepository;

    public Studio save(Studio studio) {
        return studioRepository.save(studio);
    }

    public List<Studio> findAll() {
        return studioRepository.findAll();
    }

    public Optional<Studio> findById(Long id) {
        return studioRepository.findById(id);
    }

    public void delete(Studio studio) {
        studioRepository.delete(studio);
    }
}
