package com.mutant.mutantapirest.models.dao;

import com.mutant.mutantapirest.models.entity.StatsDna;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStatsDnaDao extends CrudRepository<StatsDna, Long> {
    @Query(value = "select sd from StatsDna sd where sd.id = (select  max(id) from StatsDna)")
    StatsDna showStatsDna();
}