package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestimentoService {
    @Autowired
    private InvestimentoRepository investimentoRepository;

    public Iterable<Investimento> buscarTodosInvestimentos() {
        Iterable<Investimento> investimentos = investimentoRepository.findAll();
        return investimentos;
    }

    public Optional<Investimento> buscarPorId(Integer id) {
        Optional<Investimento> investimentoOptional = investimentoRepository.findById(id);
        if (investimentoOptional.isPresent()) {
            return investimentoOptional;
        } else {
            throw new ObjectNotFoundException("","Nao localizado o Código de investimento informado");
        }
    }

    public Investimento cadastrarInvestimento(Investimento investimento) {
        Investimento investimentoObjeto = investimentoRepository.save(investimento);
        return investimentoObjeto;
    }

    public Investimento atualizarInvestimento(Investimento investimento) {
        Optional<Investimento> investimentoOptional = buscarPorId(investimento.getId());
        if (investimentoOptional.isPresent()){
            Investimento investimentoData = investimentoOptional.get();
            if(investimento.getNome() == null){
                investimento.setNome(investimentoData.getNome());
            }
            if(investimento.getDescricao() == null){
                investimento.setDescricao(investimentoData.getDescricao());
            }
            if(investimento.getRiscoInvestimento() == null){
                investimento.setRiscoInvestimento(investimentoData.getRiscoInvestimento());
            }
            if(investimento.getTipoDeInvestimento() == null){
                investimento.setTipoDeInvestimento(investimentoData.getTipoDeInvestimento());
            }
            if(investimento.getRentabilidade() == null){
                investimento.setRentabilidade(investimentoData.getRentabilidade());
            }
            if(investimento.getVlr_min_aplicacao() == null){
                investimento.setVlr_min_aplicacao(investimentoData.getVlr_min_aplicacao());
            }
        }
        Investimento investimentoObjeto = investimentoRepository.save(investimento);
        return investimentoObjeto;
    }

    public void deletarInvestimento(Investimento investimento) {
        investimentoRepository.delete(investimento);
    }
}
