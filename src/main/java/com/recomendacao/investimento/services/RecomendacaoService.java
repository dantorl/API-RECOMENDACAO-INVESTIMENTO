package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Recomendacao;
import com.recomendacao.investimento.repositories.InvestidorRepository;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
import com.recomendacao.investimento.repositories.RecomendacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class RecomendacaoService {

    @Autowired
    private RecomendacaoRepository recomendacaoRepository;

    @Autowired
    private InvestimentoRepository investimentoRepository;

    @Autowired
    private InvestidorRepository investidorRepository;

    public Optional<Investimento> buscarInvestimentos(Integer investimentoId){
        Optional<Investimento> investimentoIterable = investimentoRepository.findById(investimentoId);
        return investimentoIterable;
    }

    public Iterable<Investidor> buscarInvestidor(List<Integer> investidorId){
        Iterable<Investidor> investidorIterable = investidorRepository.findAllById(investidorId);
        return investidorIterable;
    }

    public Optional<Recomendacao> buscarRecomendacao(Integer id){
        Optional<Recomendacao> recomendacao = recomendacaoRepository.findById(id);
        return recomendacao;
    }

    public Recomendacao criarRecomendacao(Recomendacao recomendacao){
        Recomendacao recomendacaoObjeto = recomendacaoRepository.save(recomendacao);
        return recomendacaoObjeto;
    }

    public Recomendacao atualizarRecomendacao(Recomendacao recomendacao){
        Optional<Recomendacao> recomendacaoOptional = recomendacaoRepository.findById(recomendacao.getId());
        if (recomendacaoOptional.isPresent()){
            Recomendacao recomendacaoData = recomendacaoOptional.get();

            if (recomendacao.getPerfilDeInvestidor() == null){
                recomendacao.setPerfilDeInvestidor(recomendacaoData.getPerfilDeInvestidor());
            }
            if (recomendacao.getTipoDeInvestimento() == null){
                recomendacao.setTipoDeInvestimento(recomendacaoData.getTipoDeInvestimento());
            }
            if (recomendacao.getPerc_recomendado() == null){
                recomendacao.setPerc_recomendado(recomendacaoData.getPerc_recomendado());
            }
            if (recomendacao.getInvestimentos() == null){
                recomendacao.setInvestimentos(recomendacaoData.getInvestimentos());
            }
            return recomendacaoRepository.save(recomendacao);
        } else {
            return null;
        }
    }
}