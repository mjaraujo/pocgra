package com.mjaraujo.pocgra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_studio")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "studioPartners")
    private List<Nomination> nominations = new ArrayList<>();

    public Studio(String name) {
        this.name = name;
    }
}
