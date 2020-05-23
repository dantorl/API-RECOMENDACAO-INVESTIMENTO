package com.recomendacao.investimento.controllers;

import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Recomendacao;
import com.recomendacao.investimento.services.RecomendacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoController {

        @Autowired
        RecomendacaoService recomendacaoService;

        @GetMapping("/{id}")
        public Recomendacao buscarRecomendacao(@PathVariable Integer id) {
            Optional<Recomendacao> recomendacaoOptional = recomendacaoService.buscarRecomendacao(id);

            if (recomendacaoOptional.isPresent()) {
                return recomendacaoOptional.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public ResponseEntity<Recomendacao> criarRecomendacao(@RequestBody @Valid Recomendacao recomendacao) {
            List<Integer> investimentosId = new ArrayList<>();
            for (Investimento investimento : recomendacao.getInvestimentos()){
                investimentosId.add(investimento.getId());
            }
            Iterable<Investimento> investimentoIterable = recomendacaoService.buscarInvestimentos(investimentosId);
            recomendacao.setInvestimentos((List) (investimentoIterable));

//            Optional<Investidor> investidorOptional = recomendacaoService.buscarInvestidor(recomendacao.getInvestidor().get().getId());
//            recomendacao.setInvestidor(investidorOptional);

            Recomendacao recomendacaoObjeto = recomendacaoService.criarRecomendacao(recomendacao);
            return ResponseEntity.status(201).body(recomendacaoObjeto);
        }

        @PutMapping("/{id}")
        public Recomendacao atualizarRecomendacao(@PathVariable Integer id, @RequestBody @Valid Recomendacao recomendacao) {
            recomendacao.setId(id);
            Recomendacao recomendacaoData = recomendacaoService.atualizarRecomendacao(recomendacao);

            if (recomendacaoData != null) {
                return recomendacaoData;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
}