package com.mjaraujo.pocgra.controller;

import com.mjaraujo.pocgra.dto.WinInterval;
import com.mjaraujo.pocgra.dto.WinningItem;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WinnerControllerTest {

    @LocalServerPort
    private Integer port;

    @Test
    void getAllWinnersInterval() throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + port + "/producers/winners";
        URI uri = new URI(baseUrl);

        ResponseEntity<WinInterval> result = restTemplate.getForEntity(uri, WinInterval.class);

        //Verify request succeed
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(
                true,
                Objects.requireNonNull(result.getBody()).getMax().size() > 0 &&
                        result.getBody().getMin().size() > 0
        );

        assertTrue(isInReverseOrder(result.getBody().getMax().stream().map(WinningItem::getInterval).collect(Collectors.toList())),"The max list is correct");
        assertTrue(isInAscendingOrder(result.getBody().getMin().stream().map(WinningItem::getInterval).collect(Collectors.toList())),"The max list is correct");
    }

    private Boolean isInAscendingOrder(List<Integer> intervals) {
        return intervals.stream().sorted().collect(Collectors.toList()).equals(intervals);
    }

    private Boolean isInReverseOrder(List<Integer> intervals) {
        return intervals.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).equals(intervals);
    }
}