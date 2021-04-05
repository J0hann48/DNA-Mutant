package com.mutant.mutantapirest.controllers;

import com.mutant.mutantapirest.models.entity.Dna;
import com.mutant.mutantapirest.models.entity.StatsDna;
import com.mutant.mutantapirest.models.service.IDnaService;
import com.mutant.mutantapirest.models.service.IStatsDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@RestController
@RequestMapping("/api")
public class StatsDnaController {
    @Autowired
    private IStatsDnaService iDnaStatsService;
    @Autowired
    private IDnaService dnaService;

    @GetMapping("/stats")
    public ResponseEntity<?> showStats(){
        StatsDna statsDna = new StatsDna();
        ArrayList<Dna> dnaList = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try{
            dnaList = dnaService.findAll();
            if (dnaList.isEmpty()){
                response.put("mensaje", "No existen cadenas de ADN, no se tienen estadisiticas");
            }else{
                saveStatsDna(dnaList);
                statsDna = iDnaStatsService.showStatsDna();
                response.put("Estadisticas", statsDna);
            }
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    private void saveStatsDna(ArrayList<Dna> listDna){
        long countMutant = 0;
        long countHuman = 0;
        double ratio = 0;
        String cadenaRatio = " ";
        DecimalFormatSymbols separator = new DecimalFormatSymbols();
        separator.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.#", separator);
        StatsDna statsDna = new StatsDna();
        try{
            for (Dna dna: listDna) {
                if (dna.getMutant() == false){
                    countHuman++;
                }else{
                    countMutant++;
                }
            }
            ratio = new Long(countMutant).doubleValue() /  new Long(countHuman).doubleValue();
            cadenaRatio = format.format(ratio);
            ratio = Double.parseDouble(cadenaRatio);
            statsDna.setCountMutantDna(countMutant);
            statsDna.setCountHumandna(countHuman);
            statsDna.setRatio(ratio);
            iDnaStatsService.save(statsDna);
        }catch (Exception e){
            System.out.println("StatsDnaController.saveStatsDna : " + e.getMessage());
        }
    }
}
