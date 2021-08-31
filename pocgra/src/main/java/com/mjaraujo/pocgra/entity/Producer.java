package com.mjaraujo.pocgra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_producer")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "producerPartners")
    private List<Nomination> nominations = new ArrayList<>();

    public Producer(String name) {
        this.name = name;
    }
}
