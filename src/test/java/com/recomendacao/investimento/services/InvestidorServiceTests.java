package com.recomendacao.investimento.services;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.repositories.InvestidorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

@SpringBootTest
public class InvestidorServiceTests {

    @MockBean
    InvestidorRepository investidorRepository;

    @Autowired
    InvestidorService investidorService;

    Investidor investidor;

    @BeforeEach
    public void inicializar(){
        investidor = new Investidor();
        investidor.setId(1);
        investidor.setPerfilDeInvestidor(PerfilDeInvestidor.AGRESSIVO);
        investidor.setEmail("danilo@gmail.com");
        investidor.setNome("Danilo");
        investidor.setSenha("123");
    }

    @Test
    public void testarSalvarInvestidor(){

        Mockito.when(investidorRepository.save(Mockito.any(Investidor.class))).thenReturn(investidor);

        Investidor investidorObjeto = investidorService.salvarInvestidor(investidor);

        Assertions.assertEquals(investidor, investidorObjeto);
        Assertions.assertEquals(investidor.getEmail(), investidorObjeto.getEmail());

    }
}
