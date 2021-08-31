package com.mjaraujo.pocgra.controller;

import com.mjaraujo.pocgra.entity.Nomination;
import com.mjaraujo.pocgra.service.NominationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class NominationController {

    private NominationService nominationService;

    /*@PostMapping("/nominations")
    public ResponseEntity<Nomination> save(@RequestBody Nomination nomination) {
        try {
            return new ResponseEntity<>(nominationService.save(nomination), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    @GetMapping("/nominations")
    public ResponseEntity<List<Nomination>> getAllNominations() {
        try {
            List<Nomination> nominations = nominationService.findAll();
            if (nominations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(nominations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nominations/{id}")
    public ResponseEntity<Nomination> getSingleNomination(@PathVariable Long id) {
        try {
            Optional<Nomination> nomination = nominationService.findById(id);
            return nomination.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @PutMapping("/nominations/{id}")
    public ResponseEntity<Nomination> updateNomination(@RequestBody Nomination nomination) {
        try {
            return new ResponseEntity<>(nominationService.save(nomination), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @DeleteMapping("/nominations/{id}")
    public ResponseEntity<Nomination> deleteNomination(@PathVariable Long id) {
        try {
            Optional<Nomination> nomination = nominationService.findById(id);
            if (nomination.isPresent()) {
                nominationService.delete(nomination.get());
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
