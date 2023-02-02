package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final PessoaService pessoaService;

    public EnderecoDTO cadastrarEndereco (EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoa = pessoaService.buscarPessoaPorId(enderecoCreateDTO.getIdPessoa());
        EnderecoEntity endereco = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);

        endereco.setIdPessoa(enderecoCreateDTO.getIdPessoa());
        pessoa.setEnderecos(Set.of(endereco));
        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoRepository.save(endereco), EnderecoDTO.class);

        return enderecoDTO;
    }

    public EnderecoEntity converterEmEnderecoEntity (EnderecoCreateDTO endereco) {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setIdPessoa(endereco.getIdPessoa());
        enderecoEntity.setLogradouro(endereco.getLogradouro());
        enderecoEntity.setCep(endereco.getCep());
        enderecoEntity.setNumero(endereco.getNumero());
        enderecoEntity.setCidade(endereco.getCidade());
        enderecoEntity.setTipoEndereco(endereco.getTipoEndereco());

        return enderecoEntity;
    }

    public EnderecoDTO converterEmEnderecoDTO (EnderecoEntity endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdEndereco(endereco.getIdEndereco());
        enderecoDTO.setIdPessoa(endereco.getIdPessoa());
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setTipoEndereco(endereco.getTipoEndereco());

        return enderecoDTO;
    }
}
