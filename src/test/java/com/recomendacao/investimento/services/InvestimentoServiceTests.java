package com.recomendacao.investimento.services;

import com.recomendacao.investimento.enums.RiscoInvestimento;
import com.recomendacao.investimento.enums.TipoDeInvestimento;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
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
public class InvestimentoServiceTests {

    @MockBean
    InvestimentoRepository investimentoRepository;

    @Autowired
    InvestimentoService investimentoService;

    Investimento investimento;

    @BeforeEach
    public void inicializar(){
        investimento = new Investimento();
        investimento.setId(1);
        investimento.setNome("Fellipe Roveri");
        investimento.setDescricao("Investimento");
        investimento.setRiscoInvestimento(RiscoInvestimento.BAIXO);
        investimento.setTipoDeInvestimento(TipoDeInvestimento.RENDA_FIXA);
        investimento.setRentabilidade(1.0);
        investimento.setVlr_min_aplicacao(100.00);
    }

    @Test
    public void testarCadastrarInvestimentos(){

        Mockito.when(investimentoRepository.save(Mockito.any(Investimento.class))).thenReturn(investimento);

        Investimento investimentoObjeto = investimentoService.cadastrarInvestimento(investimento);

        Assertions.assertEquals(investimento, investimentoObjeto);

    }

    @Test
    public void testarBuscarInvestimentosPorId(){

        Optional<Investimento> optionalInvestimento = Optional.of(investimento);

        Mockito.when(investimentoRepository.findById(Mockito.any(Integer.class))).thenReturn(optionalInvestimento);

        Assertions.assertEquals(investimento, optionalInvestimento.get());
    }

    @Test
    public void testarBuscaTodosInvestimentos(){

        Iterable<Investimento> investimentoIterable = Arrays.asList(investimento);

        Mockito.when(investimentoRepository.findAll()).thenReturn(investimentoIterable);

        Assertions.assertEquals(investimento, ((List<Investimento>) investimentoIterable).get(0));
    }

    @Test
    public void testarDeletarInvestimentos(){
        investimentoService.deletarInvestimento(investimento);
        Mockito.verify(investimentoRepository).delete(Mockito.any(Investimento.class));
    }

    @Test
    public void testarAtualizarInvestimentos(){

        investimento.setRentabilidade(1.0);

        Mockito.when(investimentoRepository.save(Mockito.any(Investimento.class))).thenReturn(investimento);

        Investimento investimentoObjeto = investimentoService.cadastrarInvestimento(investimento);

        Assertions.assertEquals(investimento.getRentabilidade(), investimentoObjeto.getRentabilidade());
    }

}
