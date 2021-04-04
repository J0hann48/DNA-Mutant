package com.mutant.mutantapirest.controllers;

import com.mutant.mutantapirest.models.entity.Dna;
import com.mutant.mutantapirest.models.service.IDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//@CrossOrigin(origins = {"dominio"})
@RestController
@RequestMapping("/api")
public class DnaRestController {

    @Autowired
    private IDnaService dnaService;
    @PostMapping("/mutant")
    public ResponseEntity<?> saveDna(@Valid @RequestBody Object dna) {
        Dna dnaNew = new Dna();
        Map<String, Object> response = new HashMap<>();
        boolean isMutant = false;
        String[] arrayDna = null;
        String cadenaDna = " ";
        LinkedHashMap<String, ArrayList<String>> hashMap = (LinkedHashMap<String, ArrayList<String>>) dna;
        try{
            cadenaDna = convertMapToString(hashMap);
            if (!isvalid(cadenaDna)  || cadenaDna.length() < 41){
                response.put("mensaje", "Error, cadena de ADN invalida");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            arrayDna = convertMapToArrayString(hashMap).clone();
            dnaNew.setDna(cadenaDna);
            isMutant = isMutant(arrayDna) ? true : false;
            dnaNew.setMutant(isMutant);
            dnaNew = dnaService.save(dnaNew);
        }catch (DataAccessException dt){
            response.put("mensaje", "Error al realizar el insert en base de datos");
            response.put("error", dt.getMessage().concat(": ").concat(dt.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN);
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
        return convert;
    }
    private boolean isvalid(String cadena){
        boolean isvalid = false;
        isvalid = Pattern.matches("[ACGT-]{4,41}+", cadena);
        return isvalid;
    }

    private String[] convertMapToArrayString(LinkedHashMap<String, ArrayList<String>> hashMap){
        int cont = 0;
        String[] arrayDna = null;
        for (Map.Entry<String, ArrayList<String>> entry : hashMap.entrySet()){
            arrayDna = new String[entry.getValue().size()];
            for (Object obj : entry.getValue()){
                arrayDna[cont] = obj.toString();
                cont++;
            }
        }
        System.out.println(arrayDna.length);
        return arrayDna;
    }

    private boolean isMutant(String[] dna){
        char[][] dnaChar = new char[dna.length][dna[0].length()];
        int horizontal = 0, vertical = 0, diagonal = 0;
        boolean isMutant = false;
        for (int i = 0; i < dna.length; i++){
            for (int j = 0; j < dna[i].length(); j++){
                dnaChar[j] = dna[j].toCharArray();
            }
        }
        char auxH = dnaChar[0][0];
        char auxV = dnaChar[0][0];
        char auxD = dnaChar[0][0];
        for(int x = 0; x < dna.length; x++){
            for (int c = 0; c < dnaChar[x].length; c++){
                // Horizontal
                if (auxH == dnaChar[x][c]){
                    horizontal++;
                }else if(horizontal == 3){
                    isMutant = true;
                }else{
                    auxH = dnaChar[x][c];
                    horizontal = 0;

                }
                // Vertical
                if(dnaChar[c][x] == auxV){
                    vertical++;
                }else if(vertical == 3){
                    isMutant = true;
                }else{
                    auxV = dnaChar[c][x];
                    vertical = 0;

                }
            }
            if (horizontal != 3 || vertical != 3){
                horizontal = 0;
                vertical = 0;
            }
            // Diagonal
            if(dnaChar[x][x] == auxD){
                diagonal++;
            }else if(diagonal >= 3){
                isMutant = true;
            }else{
                auxD = dnaChar[x][x];
                diagonal = 0;
            }
        }
        return isMutant;
    }
}
