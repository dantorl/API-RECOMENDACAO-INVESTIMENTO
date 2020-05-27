package com.recomendacao.investimento.services;

import com.recomendacao.investimento.enums.PerfilDeInvestidor;
import com.recomendacao.investimento.enums.RiscoInvestimento;
import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Recomendacao;
import com.recomendacao.investimento.repositories.InvestidorRepository;
import com.recomendacao.investimento.repositories.InvestimentoRepository;
import com.recomendacao.investimento.repositories.RecomendacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private InvestimentoService investimentoService;

    public Optional<Recomendacao> buscarRecomendacao(Integer id){
        Optional<Recomendacao> recomendacaoOptional = recomendacaoRepository.findById(id);
        return recomendacaoOptional;
    }

    public Recomendacao criarRecomendacao(Recomendacao recomendacao){

        List<Integer> investimentosId = new ArrayList<>();
        Iterable<Investimento> investimentoIterable;

        if (recomendacao.getInvestidor().getPerfilDeInvestidor() == PerfilDeInvestidor.CONSERVADOR){
            boolean alocacaoBaixoRisco = false;
            boolean alocacaoMedioRisco = false;
            boolean alocacaoAltoRisco = false;
            for (Investimento investimento : investimentoService.buscarTodosInvestimentos()){
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.BAIXO && alocacaoBaixoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(85.00);
                    alocacaoBaixoRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.MEDIO && alocacaoMedioRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(10.00);
                    alocacaoMedioRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.ALTO && alocacaoAltoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(5.00);
                    alocacaoAltoRisco = true;
                }
            }
            investimentoIterable = investimentoService.buscarInvestimentos(investimentosId);
            recomendacao.setInvestimentos((List) (investimentoIterable));

        } else if (recomendacao.getInvestidor().getPerfilDeInvestidor() == PerfilDeInvestidor.MODERADO){
            boolean alocacaoBaixoRisco = false;
            boolean alocacaoMedioRisco = false;
            boolean alocacaoAltoRisco = false;
            for (Investimento investimento : investimentoService.buscarTodosInvestimentos()){
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.BAIXO && alocacaoBaixoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(60.00);
                    alocacaoBaixoRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.MEDIO && alocacaoMedioRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(25.00);
                    alocacaoMedioRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.ALTO && alocacaoAltoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(15.00);
                    alocacaoAltoRisco = true;
                }
            }
            investimentoIterable = investimentoService.buscarInvestimentos(investimentosId);
            recomendacao.setInvestimentos((List) (investimentoIterable));

        } else if (recomendacao.getInvestidor().getPerfilDeInvestidor() == PerfilDeInvestidor.ARROJADO){
            boolean alocacaoBaixoRisco = false;
            boolean alocacaoMedioRisco = false;
            boolean alocacaoAltoRisco = false;
            for (Investimento investimento : investimentoService.buscarTodosInvestimentos()){
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.BAIXO && alocacaoBaixoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(40.00);
                    alocacaoBaixoRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.MEDIO && alocacaoMedioRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(35.00);
                    alocacaoMedioRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.ALTO && alocacaoAltoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(25.00);
                    alocacaoAltoRisco = true;
                }
            }
            investimentoIterable = investimentoService.buscarInvestimentos(investimentosId);
            recomendacao.setInvestimentos((List) (investimentoIterable));

        } else if (recomendacao.getInvestidor().getPerfilDeInvestidor() == PerfilDeInvestidor.AGRESSIVO){
            boolean alocacaoBaixoRisco = false;
            boolean alocacaoMedioRisco = false;
            boolean alocacaoAltoRisco = false;
            for (Investimento investimento : investimentoService.buscarTodosInvestimentos()){
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.BAIXO && alocacaoBaixoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(30.00);
                    alocacaoBaixoRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.MEDIO && alocacaoMedioRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(25.00);
                    alocacaoMedioRisco = true;
                }
                if (investimento.getRiscoInvestimento() == RiscoInvestimento.ALTO && alocacaoAltoRisco == false){
                    investimentosId.add(investimento.getId());
                    investimento.setPerc_recomendado(45.00);
                    alocacaoAltoRisco = true;
                }
            }
            investimentoIterable = investimentoService.buscarInvestimentos(investimentosId);
            recomendacao.setInvestimentos((List) (investimentoIterable));
        }

        Recomendacao recomendacaoObjeto = recomendacaoRepository.save(recomendacao);
        return recomendacaoObjeto;
    }

    public Recomendacao atualizarRecomendacao(Recomendacao recomendacao){
        Optional<Recomendacao> recomendacaoOptional = recomendacaoRepository.findById(recomendacao.getId());
        if (recomendacaoOptional.isPresent()){
            Recomendacao recomendacaoData = recomendacaoOptional.get();

            if (recomendacao.getInvestidor() == null){
                recomendacao.setInvestidor(recomendacaoData.getInvestidor());
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