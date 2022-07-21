package com.officersofttest.models.dto;

import com.officersofttest.models.entities.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private String cpf;
    private String nome;
    private String rg;
    private String cep;

    public PessoaDto(Pessoa pessoa) {
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.rg = pessoa.getRg();
        this.cep = pessoa.getCep();
    }
}
