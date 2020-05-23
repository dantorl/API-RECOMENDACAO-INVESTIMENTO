package com.recomendacao.investimento.models;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.enums.TipoDeInvestimento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Recomendacao")
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @Transient
    private Optional<Investidor> investidor;

    @NotNull
    private PerfilDeInvestidor perfilDeInvestidor;

    @NotNull
    private TipoDeInvestimento tipoDeInvestimento;

    @NotNull
    private Double perc_recomendado;

    @ManyToMany
    private List<Investimento> investimentos;

    public Recomendacao() {
    }

    public Recomendacao(Integer id, Optional<Investidor> investidor, PerfilDeInvestidor perfilDeInvestidor, TipoDeInvestimento tipoDeInvestimento, Double perc_recomendado, List<Investimento> investimentos) {
        this.id = id;
        this.investidor = investidor;
        this.perfilDeInvestidor = perfilDeInvestidor;
        this.tipoDeInvestimento = tipoDeInvestimento;
        this.perc_recomendado = perc_recomendado;
        this.investimentos = investimentos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Optional<Investidor> getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Optional<Investidor> investidor) {
        this.investidor = investidor;
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

    public Double getPerc_recomendado() {
        return perc_recomendado;
    }

    public void setPerc_recomendado(Double perc_recomendado) {
        this.perc_recomendado = perc_recomendado;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }
}