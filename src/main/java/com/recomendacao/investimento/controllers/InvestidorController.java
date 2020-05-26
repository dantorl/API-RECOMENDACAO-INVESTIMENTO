package com.recomendacao.investimento.controllers;

import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.services.InvestidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investidores")
public class InvestidorController {

    @Autowired
    private InvestidorService investidorService;

    @GetMapping
    public Iterable<Investidor> buscarTodosInvestidores(){
        return investidorService.buscarTodosInvestidores();
    }

    @GetMapping("/{id}")
    public Investidor buscarInvestidor(@PathVariable Integer id){
        Optional<Investidor> investidorOptional = investidorService.buscarPorId(id);

        if(investidorOptional.isPresent()){
            return investidorOptional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<Investidor> salvarInvestidor(@RequestBody @Valid Investidor investidor){


        Investidor investidorObjeto = investidorService.salvarInvestidor(investidor);
        return ResponseEntity.status(201).body(investidorObjeto);
    }

    @PutMapping("/{id}")
    public Investidor atualizarInvestidor(@PathVariable Integer id, @RequestBody Investidor investidor){
        investidor.setId(id);
        Investidor investidorObjeto = investidorService.atualizarInvestidor(investidor);
        return investidorObjeto;
    }

    @DeleteMapping("/{id}")
    public Investidor deletarInvestidor(@PathVariable Integer id){
        Optional<Investidor> investidorOptional = investidorService.buscarPorId(id);
        if(investidorOptional.isPresent()){
            investidorService.deletarInvestidor(investidorOptional.get());
            return investidorOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

}