package com.recomendacao.investimento.controllers;

import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Questionario;
import com.recomendacao.investimento.models.Resposta;
import com.recomendacao.investimento.services.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping
    public Iterable<Resposta> retornarTodasRespostas(){
        return respostaService.retornarTodasRespostas();
    }

    @GetMapping("/{id}")
    public Resposta retornarResposta(@PathVariable Integer id){
        Optional<Resposta> respostaOptional = respostaService.retornarRespostaPorId(id);

        if (respostaOptional.isPresent()){
            return respostaOptional.get();
        }else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Resposta> cadastrarResposta(@RequestBody Resposta resposta){
        Resposta respostaObjeto = respostaService.cadastraResposta(resposta);
        return ResponseEntity.status(201).body(respostaObjeto);
    }

    @PutMapping("/{id}")
    public Resposta atualizaResposta(@PathVariable Integer id, @RequestBody Resposta resposta){
        resposta.setId(id);
        Resposta respostaObjeto = respostaService.atualizarResposta(resposta);
        return respostaObjeto;
    }

    @DeleteMapping("/{id}")
    public Resposta deletarResposta(@PathVariable Integer id){
        Optional<Resposta> respostaOptional = respostaService.retornarRespostaPorId(id);
        if(respostaOptional.isPresent()){
            respostaService.deletarResposta(respostaOptional.get());
            return respostaOptional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}