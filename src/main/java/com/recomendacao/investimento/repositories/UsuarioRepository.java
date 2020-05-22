package com.recomendacao.investimento.repositories;

import com.recomendacao.investimento.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

}
