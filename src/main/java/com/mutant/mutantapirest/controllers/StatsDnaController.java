package com.mutant.mutantapirest.controllers;

import com.mutant.mutantapirest.models.entity.StatsDna;
import com.mutant.mutantapirest.models.service.IStatsDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatsDnaController {
    @Autowired
    private IStatsDnaService iDnaStatsService;
    @GetMapping("/stats")
    public ResponseEntity<?> showStats(){
        StatsDna statsDna = new StatsDna();
        Map<String, Object> response = new HashMap<>();
        try{
            statsDna = iDnaStatsService.showStatsDna();
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta a la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<StatsDna>(statsDna, HttpStatus.OK);
    }
}
