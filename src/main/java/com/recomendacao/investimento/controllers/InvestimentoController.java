package com.recomendacao.investimento.controllers;

import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.services.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {
    @Autowired
    private InvestimentoService investimentoService;

    @GetMapping
    public Iterable<Investimento> buscarTodosInvestimentos(){
        return investimentoService.buscarTodosInvestimentos();
    }


    @GetMapping("/{id}")
    public Investimento buscarInvestimento(@PathVariable Integer id)
    {
        Optional<Investimento> investimentoOptional;
        try{
            investimentoOptional = investimentoService.buscarPorId(id);
            return investimentoOptional.get();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Investimento> cadastrarInvestimento(@RequestBody Investimento investimento){
        Investimento investimentoObjeto = investimentoService.cadastrarInvestimento(investimento);
        return ResponseEntity.status(201).body(investimentoObjeto);
    }

    @PutMapping("/{id}")
    public Investimento atualizarInvestimento(@PathVariable Integer id, @RequestBody Investimento investimento)
    {
        investimento.setId(id);
        Optional<Investimento> investimentoOptional;
        try{
            investimentoOptional = investimentoService.buscarPorId(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        Investimento investimentoObjeto = investimentoService.atualizarInvestimento(investimento);
        return investimentoObjeto;
    }

    @DeleteMapping("/{id}")
    public Investimento deletarInvestimento(@PathVariable Integer id){
        Optional<Investimento> investimentoOptional;
        try{
            investimentoOptional = investimentoService.buscarPorId(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getLocalizedMessage());
        }
        investimentoService.deletarInvestimento(investimentoOptional.get());
        return investimentoOptional.get();
    }

}
