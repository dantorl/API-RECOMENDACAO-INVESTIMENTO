package com.recomendacao.investimento.repositories;

import com.recomendacao.investimento.models.Investidor;
import org.springframework.data.repository.CrudRepository;

public interface InvestidorRepository extends CrudRepository<Investidor, Integer> {
}
