package com.recomendacao.investimento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recomendacao.investimento.controllers.InvestimentoController;
import com.recomendacao.investimento.enums.RiscoInvestimento;
import com.recomendacao.investimento.enums.TipoDeInvestimento;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.security.JWTUtil;
import com.recomendacao.investimento.services.InvestimentoService;
import com.recomendacao.investimento.services.UsuarioService;
import org.hamcrest.CoreMatchers;
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

@WebMvcTest(InvestimentoController.class)
@Import(JWTUtil.class)
public class InvestimentoControllerTest {
    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private InvestimentoService investimentoService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    Investimento investimento;
    Investimento investimento2;

    @BeforeEach
    public void inicializar() {
        investimento = new Investimento();
        investimento.setId(1);
        investimento.setNome("FUNDO ITAU");
        investimento.setDescricao("FUNDO MULTIMERCADO");
        investimento.setRiscoInvestimento(RiscoInvestimento.ALTO);
        investimento.setTipoDeInvestimento(TipoDeInvestimento.FUNDO_MULTIMERCADO);
        investimento.setRentabilidade(0.7);
        investimento.setVlr_min_aplicacao(200.00);

        investimento2 = new Investimento();
        investimento2.setId(2);
        investimento2.setNome("CDB");
        investimento2.setDescricao("CDB DI");
        investimento2.setRiscoInvestimento(RiscoInvestimento.BAIXO);
        investimento2.setTipoDeInvestimento(TipoDeInvestimento.RENDA_FIXA);
        investimento2.setRentabilidade(0.2);
        investimento2.setVlr_min_aplicacao(10.00);
    }

    @Test
    @WithMockUser(username = "usuario@gmail.com", password = "aviao11")
    public void testarCadastroDeInvestimento() throws Exception {

        Mockito.when(investimentoService.cadastrarInvestimento(Mockito.any(Investimento.class))).thenReturn(investimento);

        String json = mapper.writeValueAsString(investimento);

        mockMvc.perform(MockMvcRequestBuilders.post("/investimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", CoreMatchers.equalTo("FUNDO ITAU")));
    }
}
