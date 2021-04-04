package com.mutant.mutantapirest.models.service;

import com.mutant.mutantapirest.models.dao.IStatsDnaDao;
import com.mutant.mutantapirest.models.entity.StatsDna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StatsDnaServiceImpl implements IStatsDnaService{

    @Autowired
    private IStatsDnaDao iStatsDao;

    @Override
    @Transactional
    public StatsDna showStatsDna() {
        return iStatsDao.showStatsDna();
    }
}
