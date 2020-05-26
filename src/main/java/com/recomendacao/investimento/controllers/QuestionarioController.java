package com.recomendacao.investimento.controllers;

import com.recomendacao.investimento.models.Questionario;
import com.recomendacao.investimento.services.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/questionario")
public class QuestionarioController {

    @Autowired
    private QuestionarioService questionarioService;

    @GetMapping
    public Iterable<Questionario> retornarTodosQuestionarios(){
        return questionarioService.retornarTodosQuestionarios();
    }

    @GetMapping("/{id}")
    public Questionario retornarQuestionario(@PathVariable Integer id){
        Optional<Questionario> questionarioOptional = questionarioService.retornarQuestionarioPorId(id);

        if (questionarioOptional.isPresent()){
            return questionarioOptional.get();
        }else
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Questionario> cadastrarQuestionario(@RequestBody Questionario questionario){
        Questionario questionarioObjeto = questionarioService.cadastrarQuestionario(questionario);
        return ResponseEntity.status(201).body(questionarioObjeto);
    }

    @PutMapping("/{id}")
    public Questionario atualizaQuestionario(@PathVariable Integer id, @RequestBody Questionario questionario){
        questionario.setId(id);
        Questionario questionarioObjeto = questionarioService.atualizarQuestionario(questionario);
        return questionarioObjeto;
    }

    @DeleteMapping("/{id}")
    public Questionario deletarQuestionario(@PathVariable Integer id){
        Optional<Questionario> questionarioOptional = questionarioService.retornarQuestionarioPorId(id);
        if(questionarioOptional.isPresent()){
            questionarioService.deletarQuestionario(questionarioOptional.get());
            return questionarioOptional.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}