package com.mutant.mutantapirest.controllers;

import com.mutant.mutantapirest.models.entity.Dna;
import com.mutant.mutantapirest.models.service.IDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//@CrossOrigin(origins = {"dominio"})
@RestController
@RequestMapping("/api")
public class DnaRestController {

    @Autowired
    private IDnaService dnaService;
    @PostMapping("/mutant")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveDna(@RequestBody Object dna) {
        Dna dnaNew = new Dna();
        Map<String, Object> response = new HashMap<>();
        String[] arrayDna = null;
        String cadenaDna = " ";
        LinkedHashMap<String, ArrayList<String>> hashMap = (LinkedHashMap<String, ArrayList<String>>) dna;
        try{
            cadenaDna = convertMapToString(hashMap);
            dnaNew.setDna(cadenaDna);
            dnaNew.setMutant(false);
            dnaNew = dnaService.save(dnaNew);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar el insert en base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "DNA save");
        response.put("dna", dnaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    private String convertMapToString(LinkedHashMap<String, ArrayList<String>> hashMap){
        String convert = " ";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()){
            for (Object obj : entry.getValue()){
                builder.append(obj).append("-");
                convert = builder.substring(0, builder.length() -1);
            }
        }
        System.out.println("String convert " + convert);
        return convert;
    }

    private String[] convertMapToArrayString(LinkedHashMap<String, ArrayList<String>> hashMap){
        String[] arrayDna = null;
        int cont = 0;
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()){
            for (Object obj : entry.getValue()){
                arrayDna[cont] = obj.toString();
                cont++;
            }
        }
        return arrayDna;
    }
}
