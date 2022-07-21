package com.officersofttest.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Campo cpf vazio")
    @NotBlank(message = "Campo cpf em branco")
    private String cpf;

    @NotNull(message = "Campo nome vazio")
    @NotBlank(message = "Campo nome em branco")
    private String nome;

    @NotNull(message = "Campo cep vazio")
    @NotBlank(message = "Campo cep em branco")
    private String cep;

    @NotNull(message = "Campo endereco vazio")
    @NotBlank(message = "Campo endereco em branco")
    private String endereco;

    @NotNull(message = "Campo numero vazio")
    private Integer numero;

    @NotNull(message = "Campo bairro vazio")
    @NotBlank(message = "Campo bairro em branco")
    private String bairro;

    private String complemento;

    @NotNull(message = "Campo municipio vazio")
    @NotBlank(message = "Campo municipio em branco")
    private String municipio;

    @NotNull(message = "Campo uf vazio")
    @NotBlank(message = "Campo uf em branco")
    private String uf;

    @NotNull(message = "Campo rg vazio")
    @NotBlank(message = "Campo rg em branco")
    private String rg;
}
