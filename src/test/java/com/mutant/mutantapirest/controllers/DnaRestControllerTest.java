package com.mutant.mutantapirest.controllers;

import com.mutant.mutantapirest.models.service.IDnaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DnaRestController.class)
@WithMockUser
public class DnaRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IDnaService dnaService;

    private final DnaRestController dnaRestController = new DnaRestController();
    private final String[] dnaTest = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

    private static final String URL = "/api/mutant";

    @Before
    public void beforeTest(){
        mockMvc = MockMvcBuilders.standaloneSetup(dnaRestController).build();
    }

    @Test
    public void saveDnaReturnOKPost() throws Exception {
        LinkedHashMap<String, ArrayList<String>> hashMap = new LinkedHashMap<>();
        ArrayList<String> lista = new ArrayList<>();
        lista.add("ATGCAA");
        lista.add("CAGTGC");
        lista.add("TGATGT");
        lista.add("AGTAGG");
        lista.add("CCCCTA");
        lista.add("TCACTG");
        hashMap.put("dna", lista);
        String dnaString = arrayToString(dnaTest);
        boolean isValid = dnaRestController.isvalid(dnaString);

        if(isValid){
            boolean isMutant = dnaRestController.isMutant(dnaTest);
            mockMvc.perform(MockMvcRequestBuilders.post(URL).param("dna", hashMap.toString())).andExpect(status().isOk());
        }else{
            mockMvc.perform(MockMvcRequestBuilders.post(URL)).andExpect(status().isForbidden());
        }

    }

    @Test
    public void saveDnaReturnBadRequestPost() throws Exception {
        LinkedHashMap<String, ArrayList<String>> hashMap = new LinkedHashMap<>();
        ArrayList<String> lista = new ArrayList<>();
        lista.add("ATGCAA");
        lista.add("COLTGC");
        lista.add("TGATGT");
        lista.add("AGRAGG");
        lista.add("CCYCTA");
        lista.add("TCACTG");
        hashMap.put("dna", lista);
        String dnaString = arrayToString(dnaTest);
        boolean isValid = dnaRestController.isvalid(dnaString);

        if(!isValid)
            mockMvc.perform(MockMvcRequestBuilders.post(URL).param("dna", hashMap.toString())).andExpect(status().isBadRequest());

    }


    private String arrayToString(String[] dnaTest){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dnaTest.length; i++) {
            sb.append(dnaTest[i]);
        }
        String dna = sb.toString();
        System.out.println(dna);
        return dna;
    }
}
