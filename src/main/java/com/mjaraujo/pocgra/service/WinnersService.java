package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.dto.WinInterval;
import com.mjaraujo.pocgra.dto.Winner;
import com.mjaraujo.pocgra.dto.WinningItem;
import com.mjaraujo.pocgra.entity.*;
import com.mjaraujo.pocgra.repository.INominationRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WinnersService {

    private final INominationRepository nominationRepository;

    public WinnersService(INominationRepository nominationRepository) {
        this.nominationRepository = nominationRepository;
    }

    public WinInterval getAllWinnersInterval() {
        WinInterval winInterval = new WinInterval();

        //Filter only winners
        List<Nomination> winnersNomination = nominationRepository.findAll()
                .stream()
                .filter(Nomination::getWinner)
                .collect(Collectors.toList());

        //Extrai produtor e ano
        List<Winner> finalWinners = new ArrayList<>();
        for (Nomination nomination : winnersNomination) {
            finalWinners.addAll(nomination.getProducerPartners()
                    .stream()
                    .map(producer -> {
                        Winner winner = new Winner();
                        winner.setYear(nomination.getYear());
                        winner.setName(producer.getName());
                        return winner;
                    }).collect(Collectors.toList())
            );
        }

        //Agrupa por produtor
        Map<String, List<Winner>> winnersByName = finalWinners
                .stream()
                .collect(Collectors.groupingBy(Winner::getName));

        //Filtra somente produtores com mais de um prêmio
        List<List<Winner>> multipleWinners = new ArrayList<>(winnersByName.values()).stream().filter(winners -> winners.size() > 1).collect(Collectors.toList());

        List<WinningItem> maxList = new ArrayList<>();
        List<WinningItem> minList = new ArrayList<>();
        for (List<Winner> multipleWinner : multipleWinners) {
            WinningItem winningItemMax = new WinningItem();
            winningItemMax.setProducer(multipleWinner.get(0).getName());
            int maxInterval = 0;
            for (int i = 1; i < multipleWinner.size(); i++) {
                int interval = multipleWinner.get(i).getYear() - multipleWinner.get(i - 1).getYear();
                if (interval > maxInterval) {
                    maxInterval = interval;
                    winningItemMax.setInterval(interval);
                    winningItemMax.setPreviousWin(multipleWinner.get(i - 1).getYear());
                    winningItemMax.setFollowingWin(multipleWinner.get(i).getYear());
                }
            }
            maxList.add(winningItemMax);

            WinningItem winningItemMin = new WinningItem();
            winningItemMin.setProducer(multipleWinner.get(0).getName());
            int minInterval = multipleWinner
                    .stream()
                    .mapToInt(Winner::getYear)
                    .max().orElseThrow(NoSuchElementException::new);

            for (int i = 1; i < multipleWinner.size(); i++) {
                int interval = multipleWinner.get(i).getYear() - multipleWinner.get(i - 1).getYear();
                if (interval < minInterval) {
                    minInterval = interval;
                    winningItemMin.setInterval(interval);
                    winningItemMin.setPreviousWin(multipleWinner.get(i - 1).getYear());
                    winningItemMin.setFollowingWin(multipleWinner.get(i).getYear());
                }
            }
            minList.add(winningItemMin);
        }

        winInterval.setMax(maxList.stream().max(Comparator.comparing(WinningItem::getInterval)).orElseGet(WinningItem::new));
        winInterval.setMin(minList.stream().min(Comparator.comparing(WinningItem::getInterval)).orElseGet(WinningItem::new));

        return winInterval;
    }


}
