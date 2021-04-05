package com.mutant.mutantapirest.models.service;

import com.mutant.mutantapirest.models.dao.IDnaDao;
import com.mutant.mutantapirest.models.entity.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class DnaServiceImpl implements IDnaService{

    @Autowired
    private IDnaDao dnaDao;

    @Override
    @Transactional
    public Dna save(Dna dna) {
        return dnaDao.save(dna);
    }

    @Override
    public ArrayList<Dna> findAll() {
        return (ArrayList<Dna>) dnaDao.findAll();
    }
}
