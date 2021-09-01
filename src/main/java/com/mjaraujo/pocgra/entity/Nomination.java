package com.mjaraujo.pocgra.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_nomination")
@Entity
@Setter
@Getter
public class Nomination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String movieTitle;
    @NonNull
    private Integer year;
    @NonNull
    private Boolean winner;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "studio_nomination",
            joinColumns = {@JoinColumn(name = "nomination_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "studio_id", referencedColumnName = "id")}
    )
    private List<Studio> studioPartners = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "producer_nomination",
            joinColumns = {@JoinColumn(name = "nomination_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "producer_id", referencedColumnName = "id")}
    )
    private List<Producer> producerPartners = new ArrayList<>();
}
