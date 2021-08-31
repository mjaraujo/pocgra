package com.mjaraujo.pocgra.controller;

import com.mjaraujo.pocgra.dto.WinInterval;
import com.mjaraujo.pocgra.service.WinnersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WinnerController {

    @Autowired
    private WinnersService winnersService;


    @GetMapping("/producers/winners")
    public ResponseEntity<WinInterval> getAllWinnersInterval() {
        try {
            WinInterval producersWinners = winnersService.getAllWinnersInterval();
            if (producersWinners == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(producersWinners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
