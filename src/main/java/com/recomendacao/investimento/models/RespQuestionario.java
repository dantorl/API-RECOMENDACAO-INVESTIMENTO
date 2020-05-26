package com.recomendacao.investimento.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RespQuestionario")
public class RespQuestionario {
    @Id //identifica que o campo abaixo é o ID da tabela mysql
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @NotNull
    private Integer id_cliente;

    @NotNull
    private Integer id_pergunta;

    @NotNull
    private String resposta;

    @NotNull
    private Integer peso;

    public RespQuestionario() {
    }

    public RespQuestionario(Integer id, @NotNull Integer id_cliente, @NotNull Integer id_pergunta, @NotNull String resposta, @NotNull Integer peso) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_pergunta = id_pergunta;
        this.resposta = resposta;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_pergunta() {
        return id_pergunta;
    }

    public void setId_pergunta(Integer id_pergunta) {
        this.id_pergunta = id_pergunta;
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
