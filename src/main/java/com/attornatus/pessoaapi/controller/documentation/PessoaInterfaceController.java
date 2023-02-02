package com.attornatus.pessoaapi.controller.documentation;

import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

public interface PessoaInterfaceController {

    @Operation(summary = "Cadastro de pessoa", description = "Realiza a inserção dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastro")
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@Valid @RequestBody PessoaCreateDTO pessoaCreateDTO);

    @Operation(summary = "Editar dados da pessoa", description = "Realiza a atualização dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/atualizar-pessoa")
    public ResponseEntity<PessoaDTO> atualizarPessoa (@Valid Integer idPessoa,
                                                      @RequestBody PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Pesquisa uma pessoa pelo seu id", description = "Realiza a pesquisa no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/consultar-uma-pessoa")
    public ResponseEntity<PessoaDTO> consultarUmaPessoa (Integer idPessoa) throws RegraDeNegocioException;

    @Operation(summary = "Pesquisa no banco através dos atributos", description = "Realiza a pesquisa dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-pessoas-com-paginacao")
    public ResponseEntity<PageDTO<PessoaDTO>> listarPessoasComPaginacao (@RequestParam(required = false) Integer idPessoa,
                                                                         @RequestParam(required = false)String nomePessoa,
                                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNascimento,
                                                                         Integer page,
                                                                         Integer size ) throws RegraDeNegocioException;
}
