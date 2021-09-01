package com.mjaraujo.pocgra.service;

import com.mjaraujo.pocgra.entity.*;
import com.mjaraujo.pocgra.repository.INominationRepository;
import com.mjaraujo.pocgra.repository.IProducerRepository;
import com.mjaraujo.pocgra.repository.IStudioRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
@AllArgsConstructor
public class CsvReaderService {

    @Autowired
    private IProducerRepository producerRepository;
    private INominationRepository nominationRepository;
    private IStudioRepository studioRepository;
    private ResourceLoader resourceLoader;


    @PostConstruct
    @Transactional
    public void init() {
        String resourceName = "movielist.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("csvdata/" + resourceName)).getFile());

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file.getPath())).withCSVParser(csvParser).build()) {
            csvReader.readAll().forEach(l -> {
                List<Studio> studios = new ArrayList<>();
                List<Producer> producers = new ArrayList<>();
                Nomination nomination = new Nomination();
                if (!l[0].equals("year")) {

                    if (l.length == 5) {
                        //nomination
                        nomination.setYear(Integer.valueOf(l[0]));
                        nomination.setMovieTitle(l[1]);
                        //studios
                        l[2] = l[2].replace(" and ", ",");
                        Arrays.stream(l[2].split(",")).forEach(std -> {
                                    std = std.trim();
                                    if (!std.isEmpty()) {
                                        Optional<Studio> one = studioRepository.findOne(studioByName(std.trim()));
                                        if (one.isPresent()) {
                                            studios.add(one.get());
                                        } else {
                                            Studio studio = new Studio(std);
                                            studios.add(studio);
                                        }
                                    }
                                }
                        );

                        //producers
                        l[3] = l[3].replace(" and ", ",");

                        Arrays.stream(l[3].split(",")).forEach(prd -> {
                                    prd = prd.trim();
                                    if (!prd.isEmpty()) {
                                        Optional<Producer> one = producerRepository.findOne(producerByName(prd.trim()));
                                        if (one.isPresent()) {
                                            producers.add(one.get());
                                        } else {
                                            Producer producer = new Producer(prd);
                                            producers.add(producer);
                                        }
                                    }
                                }
                        );
                        nomination.setWinner(l[4].equals("yes"));
                        producerRepository.saveAll(producers);
                        studioRepository.saveAll(studios);
                        nomination.getStudioPartners().addAll(studios);
                        nomination.getProducerPartners().addAll(producers);
                        System.out.println(nomination);
                        nominationRepository.save(nomination);
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Specification<Studio> studioByName(String name) {
        return (root, query, builder) -> builder.equal(root.get(Studio_.name), name.trim());
    }

    private static Specification<Producer> producerByName(String name) {
        return (root, query, builder) -> builder.equal(root.get(Producer_.name), name.trim());
    }
}
