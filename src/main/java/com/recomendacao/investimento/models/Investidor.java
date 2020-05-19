package com.recomendacao.investimento.models;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Investidor")
public class Investidor {
    @Id //identifica que o campo abaixo é o ID da tabela mysql
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera ID sequencial e único
    private Integer id;

    @Size(min = 8, max = 100, message = "O nome deve ter no minimo 8 caracteres e no maximo 100")
    private String nome;

    @Email(message = "O formato do email é invalido")
    private String email;

    private PerfilDeInvestidor perfilDeInvestidor;

    public Investidor() {
    }

    public Investidor(Integer id, @Size(min = 8, max = 100, message = "O nome deve ter no minimo 8 caracteres e no maximo 100") String nome, @Email(message = "O formato do email é invalido") String email, PerfilDeInvestidor perfilDeInvestidor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.perfilDeInvestidor = perfilDeInvestidor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilDeInvestidor getPerfilDeInvestidor() {
        return perfilDeInvestidor;
    }

    public void setPerfilDeInvestidor(PerfilDeInvestidor perfilDeInvestidor) {
        this.perfilDeInvestidor = perfilDeInvestidor;
    }
}
