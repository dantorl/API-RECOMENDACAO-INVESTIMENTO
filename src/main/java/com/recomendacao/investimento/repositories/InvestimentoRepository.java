package com.recomendacao.investimento.repositories;

import com.recomendacao.investimento.models.Investimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InvestimentoRepository extends CrudRepository<Investimento, Integer> {
}