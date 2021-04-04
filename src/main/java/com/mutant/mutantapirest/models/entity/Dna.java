package com.mutant.mutantapirest.models.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dna")
public class Dna  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String dna;
    @Column(nullable = false)
    private boolean isMutant;
    @Temporal(TemporalType.DATE)
    private Date fechaDna;

    @PrePersist
    public void prePersist(){
        fechaDna = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean getMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }

    public Date getFechaDna() {
        return fechaDna;
    }

    public void setFechaDna(Date fechaDna) {
        this.fechaDna = fechaDna;
    }
}
