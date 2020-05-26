package com.recomendacao.investimento.models;

import com.recomendacao.investimento.enums.RiscoInvestimento;
import com.recomendacao.investimento.enums.TipoDeInvestimento;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Investimento")
public class Investimento {
    @Id //identifica que o campo abaixo é o ID da tabela mysql
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @Column(name = "nome_investimento")
    @Size(min = 3, max = 100, message = "O nome deve ter no minimo 8 caracteres e no maximo 100")
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private RiscoInvestimento riscoInvestimento;

    @NotNull
    private TipoDeInvestimento tipoDeInvestimento;

    @NotNull
    private Double rentabilidade;

    @NotNull
    private Double vlr_min_aplicacao;


    public Investimento() {
    }

    public Investimento(Integer id, @Size(min = 3, max = 100, message = "O nome deve ter no minimo 8 caracteres e no maximo 100") String nome, @NotNull String descricao, @NotNull RiscoInvestimento riscoInvestimento, @NotNull TipoDeInvestimento tipoDeInvestimento, @NotNull Double rentabilidade, @NotNull Double vlr_min_aplicacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.riscoInvestimento = riscoInvestimento;
        this.tipoDeInvestimento = tipoDeInvestimento;
        this.rentabilidade = rentabilidade;
        this.vlr_min_aplicacao = vlr_min_aplicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RiscoInvestimento getRiscoInvestimento() {
        return riscoInvestimento;
    }

    public void setRiscoInvestimento(RiscoInvestimento riscoInvestimento) {
        this.riscoInvestimento = riscoInvestimento;
    }

    public TipoDeInvestimento getTipoDeInvestimento() {
        return tipoDeInvestimento;
    }

    public void setTipoDeInvestimento(TipoDeInvestimento tipoDeInvestimento) {
        this.tipoDeInvestimento = tipoDeInvestimento;
    }

    public Double getVlr_min_aplicacao() {
        return vlr_min_aplicacao;
    }

    public void setVlr_min_aplicacao(Double vlr_min_aplicacao) {
        this.vlr_min_aplicacao = vlr_min_aplicacao;
    }

    public Double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(Double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }
}
