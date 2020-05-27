
package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Investimento;
import com.recomendacao.investimento.models.Questionario;
import com.recomendacao.investimento.models.Resposta;
import com.recomendacao.investimento.repositories.RespostaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    public Iterable<Resposta> retornarTodasRespostas()
    {
        Iterable<Resposta> resposta = respostaRepository.findAll();
        return resposta;
    }

    public Resposta cadastraResposta(Resposta resposta){
        Resposta respostaObjeto = respostaRepository.save(resposta);
        return respostaObjeto;
    }

    public Optional<Resposta> retornarRespostaPorId(Integer id) {
        Optional<Resposta> respostaOptional = respostaRepository.findById(id);
        if (respostaOptional.isPresent()) {
            return respostaOptional;
        } else {
            throw new ObjectNotFoundException("","Nao localizado o Id de resposta informado");
        }
    }

    public void deletarResposta(Resposta resposta) {
        respostaRepository.delete(resposta);
    }

    public Resposta atualizarResposta(Resposta resposta) {
        Optional<Resposta> respostaOptional = retornarRespostaPorId(resposta.getId());
        if (respostaOptional.isPresent()){
            Resposta respostaData = respostaOptional.get();
            if(resposta.getIdQuestionario() == null){
                resposta.setIdQuestionario(respostaData.getIdQuestionario());
            }
            if(resposta.getResposta() == null){
                resposta.setResposta(respostaData.getResposta());
            }
            if(resposta.getPeso() == null){
                resposta.setPeso(respostaData.getPeso());
            }
        }
        Resposta respostaObjeto = respostaRepository.save(resposta);
        return respostaObjeto;
    }
}