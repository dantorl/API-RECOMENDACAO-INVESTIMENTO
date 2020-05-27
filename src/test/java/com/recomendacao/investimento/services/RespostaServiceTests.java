package com.recomendacao.investimento.services;

import com.recomendacao.investimento.enums.RiscoInvestimento;
import com.recomendacao.investimento.enums.TipoDeInvestimento;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Resposta;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
import com.recomendacao.investimento.repositories.RespostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class RespostaServiceTests {

    @MockBean
    RespostaRepository respostaRepository;

    @Autowired
    RespostaService respostaService;

    Resposta resposta;

    @BeforeEach
    public void inicializar(){
        resposta = new Resposta();
        resposta.setId(1);
        resposta.setIdQuestionario(1);
        resposta.setPeso(1);
        resposta.setResposta("Teste");
    }

    @Test
    public void testarCadastrarRespostas(){

        Mockito.when(respostaRepository.save(Mockito.any(Resposta.class))).thenReturn(resposta);

        Resposta respostaObjeto = respostaService.cadastraResposta(resposta);

        Assertions.assertEquals(resposta, respostaObjeto);

    }

    @Test
    public void testarBuscarRespostasPorId(){

        Optional<Resposta> optionalResposta = Optional.of(resposta);

        Mockito.when(respostaRepository.findById(Mockito.any(Integer.class))).thenReturn(optionalResposta);

        Assertions.assertEquals(resposta, optionalResposta.get());
    }

    @Test
    public void testarDeletarRespostas(){
        respostaService.deletarResposta(resposta);
        Mockito.verify(respostaRepository).delete(Mockito.any(Resposta.class));
    }

    @Test
    public void testarAtualizarRespostas(){

        resposta.setIdQuestionario(2);
        resposta.setPeso(2);
        resposta.setResposta("Teste 2");

        Mockito.when(respostaRepository.save(Mockito.any(Resposta.class))).thenReturn(resposta);

        Resposta respostaObjeto = respostaService.atualizarResposta(resposta);

        Assertions.assertEquals(resposta.getPeso(), respostaObjeto.getPeso());
        Assertions.assertEquals(resposta.getResposta(), respostaObjeto.getResposta());
        Assertions.assertEquals(resposta.getIdQuestionario(), respostaObjeto.getIdQuestionario());
    }

}
