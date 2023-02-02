package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.enums.TipoEndereco;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public PageDTO<EnderecoPessoaDTO> listarEnderecosPaginado (Integer idEndereco, Integer idPessoa, String nomePessoa, String logradouro, String cep, String cidade, TipoEndereco tipoEndereco, Integer page, Integer size) throws RegraDeNegocioException {
        if (page < 0 || size < 0) {
            throw new RegraDeNegocioException("Page ou size não pode ser menor que zero");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EnderecoPessoaDTO> enderecoEntities = enderecoRepository.listarEnderecosPaginado(idEndereco, idPessoa, nomePessoa, logradouro, cep, cidade, tipoEndereco, pageRequest);

        List<EnderecoPessoaDTO> enderecosPessoaDTO = enderecoEntities.getContent()
                .stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoPessoaDTO.class))
                .toList();

        if (enderecoEntities.isEmpty()) {
            throw new RegraDeNegocioException("Dados não encontrados.");
        }

        return new PageDTO<>(enderecoEntities.getTotalElements(),
                enderecoEntities.getTotalPages(),
                page,
                size,
                enderecosPessoaDTO);
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
