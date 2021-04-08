package com.mutant.mutantapirest.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StatsDnaController.class)
@WithMockUser
public class StatsDnaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final StatsDnaController controller = new StatsDnaController();

    private static final String URL = "/api/stats";
    @Before
    public void beforeTest(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showStatsDnaOKGet() throws Exception {
        mockMvc.perform(get(URL)).andExpect(status().isOk());
    }
}
