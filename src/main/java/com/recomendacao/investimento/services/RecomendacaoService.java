package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Recomendacao;
import com.recomendacao.investimento.repositories.InvestidorRepository;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
import com.recomendacao.investimento.repositories.RecomendacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Investimento> buscarInvestimentos(List<Integer> investimentosId){
        System.out.println(investimentosId);
        Iterable<Investimento> investimentoIterable = investimentoRepository.findAllById(investimentosId);
        System.out.println(investimentoIterable);
        return investimentoIterable;
    }

    public Optional<Investidor> buscarInvestidor(Integer investidorId){
        Optional<Investidor> investidorOptional = investidorRepository.findById(investidorId);
        return investidorOptional;
    }

    public Optional<Recomendacao> buscarRecomendacao(Integer id){
        Optional<Recomendacao> recomendacaoOptional = recomendacaoRepository.findById(id);
        return recomendacaoOptional;
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