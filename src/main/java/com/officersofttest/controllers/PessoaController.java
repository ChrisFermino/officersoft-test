package com.officersofttest.controllers;

import com.officersofttest.models.dto.PessoaDto;
import com.officersofttest.models.entities.Pessoa;
import com.officersofttest.services.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> savePessoa(@Valid @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(pessoaService.save(pessoa));
    }

    @PutMapping
    public ResponseEntity<String> editPessoa(@RequestParam String cpf, @RequestBody Pessoa pessoa) {
        pessoaService.edit(pessoa, cpf);
        return ResponseEntity.ok().body("Registro<Pessoa> alterado com sucesso");
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDto>> getPessoa(@RequestParam(required = false) String cpf,
                                                     @RequestParam(required = false) String nome) {
        return ResponseEntity.ok().body(pessoaService.findPessoa(cpf, nome));
    }

    @DeleteMapping
    public ResponseEntity<String> deletePessoa(@RequestParam Long id) {
        pessoaService.deleteByCpf(id);
        return ResponseEntity.ok().body("Registro<Pessoa> excluído com sucesso");
    }
}
