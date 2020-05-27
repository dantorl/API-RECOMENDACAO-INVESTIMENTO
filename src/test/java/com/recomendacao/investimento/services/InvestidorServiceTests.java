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
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testarAtualizarInvestidor(){

        investidor.setEmail("novoemailservico@gmail.com");

        Mockito.when(investidorRepository.save(Mockito.any(Investidor.class))).thenReturn(investidor);

        Investidor investidorObjeto = investidorService.atualizarInvestidor(investidor);

        Assertions.assertEquals(investidor.getEmail(), investidorObjeto.getEmail());
    }

    @Test
    public void testarBuscarInvestidorPorId(){

        Optional<Investidor> optionalLead = Optional.of(investidor);

        Mockito.when(investidorRepository.findById(Mockito.any(Integer.class))).thenReturn(optionalLead);

        Assertions.assertEquals(investidor, optionalLead.get());
    }

    @Test
    public void testarBuscaTodosInvestidores(){

        Iterable<Investidor> investidorIterable = Arrays.asList(investidor);

        Mockito.when(investidorService.buscarTodosInvestidores()).thenReturn(investidorIterable);

        Assertions.assertEquals(investidor, ((List<Investidor>) investidorIterable).get(0));
    }


    @Test
    public void testarDeletarInvestidor(){
        investidorService.deletarInvestidor(investidor);
        Mockito.verify(investidorRepository).delete(Mockito.any(Investidor.class));
    }

}
