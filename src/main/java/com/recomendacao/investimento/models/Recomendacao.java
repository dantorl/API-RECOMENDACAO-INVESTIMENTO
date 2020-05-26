package com.recomendacao.investimento.models;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.enums.TipoDeInvestimento;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Recomendacao")
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @NotNull
    private Integer id_investidor;

    @NotNull
    private PerfilDeInvestidor perfilDeInvestidor;

    @NotNull
    private TipoDeInvestimento tipoDeInvestimento;

    @NotNull
    private double perc_recomendado;

    @NotNull
    private Integer id_invest_recomendado;

    @NotNull
    private String nome_invest_recomendado;

    @NotNull
    private double rentabilidade_invest_recomendado;

    public Recomendacao() {
    }

    public Recomendacao(Integer id, @NotNull Integer id_investidor, @NotNull PerfilDeInvestidor perfilDeInvestidor, @NotNull TipoDeInvestimento tipoDeInvestimento, @NotNull double perc_recomendado, @NotNull Integer id_invest_recomendado, @NotNull String nome_invest_recomendado, @NotNull double rentabilidade_invest_recomendado) {
        this.id = id;
        this.id_investidor = id_investidor;
        this.perfilDeInvestidor = perfilDeInvestidor;
        this.tipoDeInvestimento = tipoDeInvestimento;
        this.perc_recomendado = perc_recomendado;
        this.id_invest_recomendado = id_invest_recomendado;
        this.nome_invest_recomendado = nome_invest_recomendado;
        this.rentabilidade_invest_recomendado = rentabilidade_invest_recomendado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_investidor() {
        return id_investidor;
    }

    public void setId_investidor(Integer id_investidor) {
        this.id_investidor = id_investidor;
    }

    public PerfilDeInvestidor getPerfilDeInvestidor() {
        return perfilDeInvestidor;
    }

    public void setPerfilDeInvestidor(PerfilDeInvestidor perfilDeInvestidor) {
        this.perfilDeInvestidor = perfilDeInvestidor;
    }

    public TipoDeInvestimento getTipoDeInvestimento() {
        return tipoDeInvestimento;
    }

    public void setTipoDeInvestimento(TipoDeInvestimento tipoDeInvestimento) {
        this.tipoDeInvestimento = tipoDeInvestimento;
    }

    public double getPerc_recomendado() {
        return perc_recomendado;
    }

    public void setPerc_recomendado(double perc_recomendado) {
        this.perc_recomendado = perc_recomendado;
    }

    public Integer getId_invest_recomendado() {
        return id_invest_recomendado;
    }

    public void setId_invest_recomendado(Integer id_invest_recomendado) {
        this.id_invest_recomendado = id_invest_recomendado;
    }

    public String getNome_invest_recomendado() {
        return nome_invest_recomendado;
    }

    public void setNome_invest_recomendado(String nome_invest_recomendado) {
        this.nome_invest_recomendado = nome_invest_recomendado;
    }

    public double getRentabilidade_invest_recomendado() {
        return rentabilidade_invest_recomendado;
    }

    public void setRentabilidade_invest_recomendado(double rentabilidade_invest_recomendado) {
        this.rentabilidade_invest_recomendado = rentabilidade_invest_recomendado;
    }
}
