package com.attornatus.pessoaapi.controller.documentation;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.enums.TipoEndereco;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface EnderecoInterfaceController {

    @Operation(summary = "Cadastro de endereço", description = "Realiza a inserção dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/cadastro")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@Valid
                                                        @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Pesquisa no banco através dos atributos", description = "Realiza a pesquisa dos dados no banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/listar-enderecos-com-paginacao")
    public ResponseEntity<PageDTO<EnderecoPessoaDTO>> listarPessoasComPaginacao (@RequestParam(required = false) Integer idEndereco,
                                                                                 @RequestParam(required = false) Integer idPessoa,
                                                                                 @RequestParam(required = false) String nomePessoa,
                                                                                 @RequestParam(required = false) String logradouro,
                                                                                 @RequestParam(required = false) String cep,
                                                                                 @RequestParam(required = false) String cidade,
                                                                                 @RequestParam(required = false) TipoEndereco tipoEndereco,
                                                                                 Integer page,
                                                                                 Integer size ) throws RegraDeNegocioException;
}
