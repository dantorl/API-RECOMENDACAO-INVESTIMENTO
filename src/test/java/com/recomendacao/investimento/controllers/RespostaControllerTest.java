package com.recomendacao.investimento.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Resposta;
import com.recomendacao.investimento.security.JWTUtil;
import com.recomendacao.investimento.services.InvestimentoService;
import com.recomendacao.investimento.services.RespostaService;
import com.recomendacao.investimento.services.UsuarioService;
import org.hamcrest.CoreMatchers;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(RespostaController.class)
@Import(JWTUtil.class)
public class RespostaControllerTest {

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private RespostaService respostaService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    Resposta resposta;
    Resposta resposta2;

    @BeforeEach
    public void inicializar() {
        resposta = new Resposta();
        resposta.setId(1);
        resposta.setIdQuestionario(1);
        resposta.setPeso(1);
        resposta.setResposta("Teste");

        resposta2 = new Resposta();
        resposta2.setId(2);
        resposta2.setIdQuestionario(2);
        resposta2.setPeso(2);
        resposta2.setResposta("Teste2");
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarRespostaPorId() throws Exception {
        Optional<Resposta> respostaOptional = Optional.of(resposta);

        Mockito.when(respostaService.retornarRespostaPorId(Mockito.anyInt())).thenReturn(respostaOptional);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/resposta/" + resposta.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(1)));
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarRespostaPorIdErro() throws Exception {
        Optional<Resposta> respostaOptional = Optional.empty();
        Mockito.when(respostaService.retornarRespostaPorId(Mockito.anyInt())).thenReturn(respostaOptional);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/resposta/"+resposta.getId()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarTodasRespostas() throws Exception {

        Iterable<Resposta> respostaIterable = Arrays.asList(resposta, resposta2);

        Mockito.when(respostaService.retornarTodasRespostas()).thenReturn(respostaIterable);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/resposta"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", CoreMatchers.equalTo(2)));
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarTodasRespostasVazio() throws Exception {

        Iterable<Resposta> respostaIterable = Arrays.asList();

        Mockito.when(respostaService.retornarTodasRespostas()).thenReturn(respostaIterable);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/resposta"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarAtualizarResposta() throws Exception {
        Optional<Resposta> respostaOptional = Optional.of(resposta);

        resposta.setResposta("Teste Resposta");

        Mockito.when(respostaService.atualizarResposta(Mockito.any(Resposta.class))).thenReturn(resposta);
        Mockito.when(respostaService.retornarRespostaPorId(Mockito.anyInt())).thenReturn(respostaOptional);

        String json = mapper.writeValueAsString(resposta);

        mockMvc.perform(MockMvcRequestBuilders.put("/resposta/"+resposta.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.resposta", CoreMatchers.equalTo("Teste Resposta")));
    }
/*
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarAtualizarRespostaErro() throws Exception {
        Optional<Resposta> respostaOptional = Optional.of(resposta);

        resposta.setResposta("Teste Resposta");

        Mockito.when(respostaService.atualizarResposta(Mockito.any(Resposta.class))).thenReturn(null);
        Mockito.when(respostaService.retornarRespostaPorId(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("","Nao localizado o Id de resposta informado"));

        String json = mapper.writeValueAsString(resposta);

        mockMvc.perform(MockMvcRequestBuilders.put("/resposta/"+resposta.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
*/
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarDeleteResposta() throws Exception {
        Optional<Resposta> respostaOptional = Optional.of(resposta);

        Mockito.when(respostaService.retornarRespostaPorId(Mockito.anyInt())).thenReturn(respostaOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/resposta/"+resposta.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(1)));
        Mockito.verify(respostaService, Mockito.times(1)).deletarResposta(Mockito.any(Resposta.class));

    }

}