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
    private Optional<Investidor> investidores;

    @NotNull
    private PerfilDeInvestidor perfilDeInvestidor;

    @NotNull
    private TipoDeInvestimento tipoDeInvestimento;

    @NotNull
    private Double perc_recomendado;

    @ManyToMany
    @NotNull
    private List<Investimento> investimentos;

    public Recomendacao() {
    }

    public Recomendacao(Integer id, Optional<Investidor> investidores, PerfilDeInvestidor perfilDeInvestidor, TipoDeInvestimento tipoDeInvestimento, Double perc_recomendado, List<Investimento> investimentos) {
        this.id = id;
        this.investidores = investidores;
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

    public Optional<Investidor> getInvestidores() {
        return investidores;
    }

    public void setInvestidores(Optional<Investidor> investidores) {
        this.investidores = investidores;
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