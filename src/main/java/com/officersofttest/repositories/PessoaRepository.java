package com.officersofttest.repositories;

import com.officersofttest.models.entities.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

//  @Query("SELECT new com.officersofttest.models.dto.PessoaDto(p.cpf, p.nome, p.rg, p.cep) FROM Pessoa p WHERE (:cpf is null or p.cpf = :cpf) and (:nome is null or p.nome = :nome)")

    List<Pessoa> findByNomeContaining(String nome);

    Optional<Pessoa> findByCpf(String cpf);
}