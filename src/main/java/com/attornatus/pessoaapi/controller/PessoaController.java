package com.attornatus.pessoaapi.controller;

import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping("/cadastro")
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@Valid
                                                     @RequestBody PessoaCreateDTO pessoaCreateDTO){
        log.info("Iniciando cadastro do usuário");
        PessoaDTO pessoaDTO = pessoaService.cadastrarPessoa(pessoaCreateDTO);
        log.info("Cadastro realizado com sucesso");
        return new ResponseEntity<>(pessoaDTO, HttpStatus.CREATED);
    }

}
