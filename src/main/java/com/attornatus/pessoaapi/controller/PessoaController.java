package com.attornatus.pessoaapi.controller;

import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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

    @PutMapping("/atualizar-pessoa")
    public ResponseEntity<PessoaDTO> atualizarPessoa (@Valid
                                                      Integer idPessoa,
                                                      @RequestBody PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        log.info("Editando cadastro do usuário");
        PessoaDTO pessoaDTO = pessoaService.editarPessoa(idPessoa, pessoaCreateDTO);
        log.info("Cadastro atualizado com sucesso");

        return new ResponseEntity<>(pessoaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/consultar-uma-pessoa")
    public ResponseEntity<PessoaDTO> consultarUmaPessoa (Integer idPessoa) throws RegraDeNegocioException {
        log.info("Consultando usuário");
        PessoaDTO pessoaDTO = pessoaService.consultarUmaPessoa(idPessoa);
        log.info("Consulta realizada com sucesso");

        return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
    }

    @GetMapping("/listar-pessoas-com-paginacao")
    public ResponseEntity<PageDTO<PessoaDTO>> listarPessoasComPaginacao (@RequestParam(required = false) Integer idPessoa,
                                                                         @RequestParam(required = false)String nomePessoa,
                                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
                                                                         Integer page,
                                                                         Integer size ) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.listarPessoasComPaginacao(idPessoa, nomePessoa, dataNascimento, page, size), HttpStatus.OK);
    }

}
