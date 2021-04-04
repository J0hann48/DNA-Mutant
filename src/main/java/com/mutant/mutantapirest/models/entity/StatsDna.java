package com.mutant.mutantapirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stats_dna")
public class StatsDna  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "count_mutant_dna")
    long countMutantDna;
    @Column(name = "count_human_dna")
    long countHumanDna;
    double ratio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public long getCountHumandna() {
        return countHumanDna;
    }

    public void setCountHumandna(long countHumandna) {
        this.countHumanDna = countHumandna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
