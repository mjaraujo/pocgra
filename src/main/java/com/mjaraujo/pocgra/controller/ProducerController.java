package com.mjaraujo.pocgra.controller;

import com.mjaraujo.pocgra.entity.Producer;
import com.mjaraujo.pocgra.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class ProducerController {

    private ProducerService producerService;

    @PostMapping("/producers")
    public ResponseEntity<Producer> save(@RequestBody Producer producer) {
        try {
            return new ResponseEntity<>(producerService.save(producer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producers")
    public ResponseEntity<List<Producer>> getAllProducers() {
        try {
            List<Producer> producers = producerService.findAll();
            if (producers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(producers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producers/{id}")
    public ResponseEntity<Producer> getSingleProducer(@PathVariable Long id) {
        try {
            Optional<Producer> producer = producerService.findById(id);
            return producer.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/producers/{id}")
    public ResponseEntity<Producer> updateProducer(@RequestBody Producer producer) {
        try {
            return new ResponseEntity<>(producerService.save(producer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/producers/{id}")
    public ResponseEntity<Producer> deleteProducer(@PathVariable Long id) {
        try {
            Optional<Producer> producer = producerService.findById(id);
            if (producer.isPresent()) {
                producerService.delete(producer.get());
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
