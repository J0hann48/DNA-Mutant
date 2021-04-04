package com.mutant.mutantapirest.models.service;

import com.mutant.mutantapirest.models.entity.StatsDna;
import org.springframework.data.jpa.repository.Query;

public interface IStatsDnaService {
    public StatsDna showStatsDna();
}
