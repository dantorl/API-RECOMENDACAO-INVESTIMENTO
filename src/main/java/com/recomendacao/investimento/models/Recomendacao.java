package com.recomendacao.investimento.models;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.enums.TipoDeInvestimento;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private Double perc_recomendado;

    @ManyToMany
    @NotNull
    private List<Investimento> investimentos;

    public Recomendacao() {
    }

    public Recomendacao(Integer id, @NotNull Integer id_investidor, @NotNull PerfilDeInvestidor perfilDeInvestidor, @NotNull TipoDeInvestimento tipoDeInvestimento, @NotNull Double perc_recomendado, @NotNull List<Investimento> investimentos) {
        this.id = id;
        this.id_investidor = id_investidor;
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