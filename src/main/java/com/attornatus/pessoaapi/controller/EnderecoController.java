package com.attornatus.pessoaapi.controller;

import com.attornatus.pessoaapi.controller.documentation.EnderecoInterfaceController;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.enums.TipoEndereco;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController implements EnderecoInterfaceController {

    private final EnderecoService enderecoService;

    @Override
    @PostMapping("/cadastro")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@Valid
                                                     @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        log.info("Iniciando cadastro do endereço");
        EnderecoDTO enderecoDTO = enderecoService.cadastrarEndereco(enderecoCreateDTO);
        log.info("Cadastro realizado com sucesso");
        return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/listar-enderecos-com-paginacao")
    public ResponseEntity<PageDTO<EnderecoPessoaDTO>> listarPessoasComPaginacao (@RequestParam(required = false) Integer idEndereco,
                                                                                 @RequestParam(required = false) Integer idPessoa,
                                                                                 @RequestParam(required = false) String nomePessoa,
                                                                                 @RequestParam(required = false) String logradouro,
                                                                                 @RequestParam(required = false) String cep,
                                                                                 @RequestParam(required = false) String cidade,
                                                                                 @RequestParam(required = false) TipoEndereco tipoEndereco,
                                                                                 Integer page,
                                                                                 Integer size ) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.listarEnderecosPaginado(idEndereco, idPessoa, nomePessoa, logradouro, cep, cidade, tipoEndereco,  page, size), HttpStatus.OK);
    }
}
