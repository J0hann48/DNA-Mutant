package com.mutant.mutantapirest.models.service;

import com.mutant.mutantapirest.models.entity.Dna;
import com.mutant.mutantapirest.models.entity.StatsDna;

import java.util.ArrayList;

public interface IDnaService {
    public Dna save(Dna dna);
    public ArrayList<Dna> findAll();
}
