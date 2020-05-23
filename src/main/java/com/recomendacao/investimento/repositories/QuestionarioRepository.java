package com.recomendacao.investimento.repositories;

import com.recomendacao.investimento.models.Investimento;
import org.springframework.data.repository.CrudRepository;

public interface QuestionarioRepository extends CrudRepository<Investimento, Integer> {
}