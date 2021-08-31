package com.mjaraujo.pocgra.controller;

import com.mjaraujo.pocgra.entity.Studio;
import com.mjaraujo.pocgra.service.StudioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudioController {

    private StudioService studioService;

    @PostMapping("/studios")
    public ResponseEntity<Studio> save(@RequestBody Studio studio) {
        try {
            return new ResponseEntity<>(studioService.save(studio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studios")
    public ResponseEntity<List<Studio>> getAllStudios() {
        try {
            List<Studio> studios = studioService.findAll();
            if (studios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studios/{id}")
    public ResponseEntity<Studio> getSingleStudio(@PathVariable Long id) {
        try {
            Optional<Studio> studio = studioService.findById(id);
            return studio.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/studios/{id}")
    public ResponseEntity<Studio> updateStudio(@RequestBody Studio studio) {
        try {
            return new ResponseEntity<>(studioService.save(studio), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/studios/{id}")
    public ResponseEntity<Studio> deleteStudio(@PathVariable Long id) {
        try {
            Optional<Studio> studio = studioService.findById(id);
            if (studio.isPresent()) {
                studioService.delete(studio.get());
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
