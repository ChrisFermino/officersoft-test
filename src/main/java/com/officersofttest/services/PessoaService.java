package com.officersofttest.services;

import com.officersofttest.core.excepetions.ObjectNotFound;
import com.officersofttest.core.excepetions.WrongParameter;
import com.officersofttest.models.dto.PessoaDto;
import com.officersofttest.models.entities.Pessoa;
import com.officersofttest.repositories.PessoaRepository;
import com.officersofttest.util.ValidaCPF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa save(Pessoa pessoa) {
        ValidaCPF validaCPF = new ValidaCPF();
        if (!validaCPF.valida(pessoa.getCpf())) {
            throw new WrongParameter("O campo CPF está incorreto.");
        }
        if ((pessoaRepository.findByCpf(pessoa.getCpf())).isPresent()) {
            throw new WrongParameter("Este CPF já está cadastrado.");
        }
        return pessoaRepository.save(pessoa);
    }

    public void edit(Pessoa pessoa, String cpf) {
        if (!pessoa.getCpf().equals(cpf)) {
            throw new WrongParameter("O campo CPF não pode ser alterado");
        }
        findPessoa(cpf, null);
        pessoaRepository.save(pessoa);
    }

    public Page<PessoaDto> findPessoa(String cpf, String nome) {
        ValidaCPF validaCPF = new ValidaCPF();
        if (cpf != null && validaCPF.valida(cpf)) {
            PessoaDto pessoaDto = new PessoaDto(pessoaRepository.findByCpf(cpf).get());
            return new PageImpl<PessoaDto>(new ArrayList<>(Arrays.asList(pessoaDto)));
        } else if (cpf != null) throw new WrongParameter("O campo CPF não está informado corretamente.");

        if (Objects.nonNull(pessoaRepository.findByNomeContaining(nome))) {
            List<PessoaDto> pessoaDtos = convertPessoaDto(pessoaRepository.findByNomeContaining(nome));
            return new PageImpl<PessoaDto>(pessoaDtos);
        }
        throw new ObjectNotFound("Pessoa não encontrada!");
    }

    private List<PessoaDto> convertPessoaDto(List<Pessoa> pessoas) {
        return pessoas.stream().map(this::mapper).collect(Collectors.toList());
    }

    private PessoaDto mapper(Pessoa pessoa) {
        return (PessoaDto) modelMapper.map(pessoa, PessoaDto.class);
    }

    public void deleteByCpf(Long id) {
        pessoaRepository.deleteById(id);
    }
}