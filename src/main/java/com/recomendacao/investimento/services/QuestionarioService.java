package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Questionario;
import com.recomendacao.investimento.repositories.QuestionarioRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionarioService {

    @Autowired
    private QuestionarioRepository questionarioRepository;

    public Iterable<Questionario> retornarTodosQuestionarios()
    {
        Iterable<Questionario> questionarios = questionarioRepository.findAll();
        return questionarios;
    }

    public Questionario cadastrarQuestionario(Questionario questionario){
        Questionario questionarioObjeto = questionarioRepository.save(questionario);
        return  questionarioObjeto;
    }

    public Optional<Questionario> retornarQuestionarioPorId(Integer id) {
        Optional<Questionario> questionarioOptional = questionarioRepository.findById(id);
        return questionarioOptional;
    }

    public void deletarQuestionario(Questionario questionario) {
        questionarioRepository.delete(questionario);
    }

    public Questionario atualizarQuestionario(Questionario questionario) {
        Optional<Questionario> questionarioOptional = retornarQuestionarioPorId(questionario.getId());
        if (questionarioOptional.isPresent()){
            Questionario questionarioData = questionarioOptional.get();
            if(questionario.getPergunta() == null){
                questionario.setPergunta(questionarioData.getPergunta());
            }
        }
        Questionario questionarioObjeto = questionarioRepository.save(questionario);
        return questionarioObjeto;
    }
}