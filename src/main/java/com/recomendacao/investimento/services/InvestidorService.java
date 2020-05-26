package com.recomendacao.investimento.services;

import com.recomendacao.investimento.models.Investidor;
import com.recomendacao.investimento.repositories.InvestidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class InvestidorService {

    @Autowired
    private InvestidorRepository investidorRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Iterable<Investidor> buscarTodosInvestidores() {
        Iterable<Investidor> investidores = investidorRepository.findAll();
        return investidores;
    }

    public Optional<Investidor> buscarPorId(Integer id) {
        Optional<Investidor> investidorOptional = investidorRepository.findById(id);
        return investidorOptional;
    }

    public Investidor salvarInvestidor(Investidor investidor) {
        Investidor investidorObjeto = investidorRepository.save(investidor);
        return investidor;
    }

    public Investidor atualizarInvestidor(Investidor investidor) {
        Optional<Investidor> investidorOptional = buscarPorId(investidor.getId());
            if (investidorOptional.isPresent()){
            Investidor investidorData = investidorOptional.get();

            if(investidor.getNome() == null){
                investidor.setNome(investidorData.getNome());
            }
            if(investidor.getEmail() == null){
                investidor.setEmail(investidorData.getEmail());
            }
            if(investidor.getSenha() == null){
                investidor.setSenha(investidorData.getSenha());
            }
            if(investidor.getPerfilDeInvestidor() == null){
                investidor.setPerfilDeInvestidor(investidorData.getPerfilDeInvestidor());
            }
        }
        String senha = investidor.getSenha();
        String encode = bCryptPasswordEncoder.encode(senha);
        investidor.setSenha(encode);
        Investidor investidorObjeto = investidorRepository.save(investidor);
        return investidorObjeto;
}

    public void deletarInvestidor(Investidor investidor){
        investidorRepository.delete(investidor);
    }
}
