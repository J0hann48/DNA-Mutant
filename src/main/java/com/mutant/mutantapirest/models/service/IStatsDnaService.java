package com.mutant.mutantapirest.models.service;

import com.mutant.mutantapirest.models.entity.StatsDna;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface IStatsDnaService {
    public StatsDna showStatsDna();
    public StatsDna save(StatsDna statsDna);
}
