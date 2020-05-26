package com.recomendacao.investimento.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Resposta")
public class Resposta {

    @Id //identifica que o campo abaixo é o ID da tabela mysql
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @NotNull
    private Integer idQuestionario;

    @NotNull
    private String resposta;

    @NotNull
    private Integer peso;

    public Resposta() {
    }

    public Resposta(Integer id, @NotNull Integer idQuestionario, @NotNull String resposta, @NotNull Integer peso) {
        this.id = id;
        this.idQuestionario = idQuestionario;
        this.resposta = resposta;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}