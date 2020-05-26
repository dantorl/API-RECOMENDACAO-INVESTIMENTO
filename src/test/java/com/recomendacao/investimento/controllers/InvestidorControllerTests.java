package com.recomendacao.investimento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.services.InvestidorService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(InvestidorController.class)
public class InvestidorControllerTests {

    @MockBean
    InvestidorService investidorService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    Investidor investidor;

    @BeforeEach
    public void iniciar(){
        investidor = new Investidor();
        investidor.setNome("Danilo");
        investidor.setEmail("danilo@gmail.com");
        investidor.setSenha("123");
        investidor.setPerfilDeInvestidor(PerfilDeInvestidor.AGRESSIVO);
    }

    @Test
    public void testarCadastroDeInvestidor() throws Exception {

        Mockito.when(investidorService.salvarInvestidor(Mockito.any(Investidor.class))).thenReturn(investidor);

        String json = mapper.writeValueAsString(investidor);

        mockMvc.perform(MockMvcRequestBuilders.post("/investidores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
}

