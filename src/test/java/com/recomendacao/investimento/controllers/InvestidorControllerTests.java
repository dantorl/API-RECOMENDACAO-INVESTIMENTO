package com.recomendacao.investimento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Usuario;
import com.recomendacao.investimento.security.JWTUtil;
import com.recomendacao.investimento.services.InvestidorService;
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

@WebMvcTest(InvestidorController.class)
@Import(JWTUtil.class)
public class InvestidorControllerTests {
    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    InvestidorService investidorService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    Investidor investidor;
    Investidor investidor2;
    Usuario usuario;

    @BeforeEach
    public void iniciar(){
        investidor = new Investidor();
        investidor.setId(1);
        investidor.setNome("Danilo Teste");
        investidor.setEmail("danilo@gmail.com");
        investidor.setSenha("123");
        investidor.setPerfilDeInvestidor(PerfilDeInvestidor.AGRESSIVO);

        investidor2 = new Investidor();
        investidor2.setId(2);
        investidor2.setNome("Danilo Teste");
        investidor2.setEmail("danilo@gmail.com");
        investidor2.setSenha("123");
        investidor2.setPerfilDeInvestidor(PerfilDeInvestidor.AGRESSIVO);

        usuario = new Usuario();
        usuario.setNome("Danilo Teste");
        usuario.setEmail("danilo@gmail.com");
        usuario.setSenha("123");
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarCadastroDeInvestidor() throws Exception {

        Mockito.when(investidorService.salvarInvestidor(Mockito.any(Investidor.class))).thenReturn(investidor);
        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);

        String json = mapper.writeValueAsString(investidor);

        mockMvc.perform(MockMvcRequestBuilders.post("/investidores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarInvestidor() throws Exception {
        Optional<Investidor> investidorOptional = Optional.of(investidor);

        Mockito.when(investidorService.buscarPorId(Mockito.anyInt())).thenReturn(investidorOptional);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/investidores/" + investidor.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(1)));
    }
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarInvestidorErro() throws Exception {
        Optional<Investidor> investidorOptional = Optional.empty();
        Mockito.when(investidorService.buscarPorId(Mockito.anyInt())).thenReturn(investidorOptional);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/investidores/"+investidor.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarBuscarTodosInvestidores() throws Exception {

        Iterable<Investidor> investidorIterable = Arrays.asList(investidor, investidor2);

        Mockito.when(investidorService.buscarTodosInvestidores()).thenReturn(investidorIterable);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/investidores"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", CoreMatchers.equalTo(2)));
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarDeleteInvestidor() throws Exception {
        Optional<Investidor> investidorOptional = Optional.of(investidor);

        Mockito.when(investidorService.buscarPorId(Mockito.anyInt())).thenReturn(investidorOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/investidores/"+investidor.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(1)));
        Mockito.verify(investidorService, Mockito.times(1)).deletarInvestidor(Mockito.any(Investidor.class));

    }
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarDeleteInvestidorErro() throws Exception {
        Optional<Investidor> investidorOptional = Optional.empty();

        Mockito.when(investidorService.buscarPorId(Mockito.anyInt())).thenReturn(investidorOptional);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/investidores/"+investidor.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(investidorService, Mockito.times(0)).deletarInvestidor(Mockito.any(Investidor.class));

    }
    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarAtualizarInvestidor() throws Exception {
        Optional<Investidor> investidorOptional = Optional.of(investidor);

        investidor.setNome("Teste Investidor");

        Mockito.when(investidorService.atualizarInvestidor(Mockito.any(Investidor.class))).thenReturn(investidor);
        Mockito.when(investidorService.buscarPorId(Mockito.anyInt())).thenReturn(investidorOptional);
        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);

        String json = mapper.writeValueAsString(investidor);

        mockMvc.perform(MockMvcRequestBuilders.put("/investidores/"+investidor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", CoreMatchers.equalTo("Teste Investidor")));
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarCadastroDeInvestidorErro() throws Exception {

        Mockito.when(investidorService.salvarInvestidor(Mockito.any(Investidor.class))).thenReturn(investidor);
        Mockito.when(usuarioService.salvarUsuario(Mockito.any(Usuario.class))).thenThrow(new RuntimeException("Esse email já existe"));

        String json = mapper.writeValueAsString(investidor);

        mockMvc.perform(MockMvcRequestBuilders.post("/investidores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}

