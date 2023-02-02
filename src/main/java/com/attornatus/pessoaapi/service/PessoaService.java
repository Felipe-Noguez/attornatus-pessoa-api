package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaDTO cadastrarPessoa(PessoaCreateDTO pessoaCreateDTO) {
        PessoaEntity pessoa = converterEmPessoaEntity(pessoaCreateDTO);

        pessoa.setNome(pessoaCreateDTO.getNome());
        pessoa.setDataNascimento(pessoaCreateDTO.getDataNascimento());

        pessoaRepository.save(pessoa);

        return converterEmPessoaDTO(pessoa);
    }

    public PessoaDTO editarPessoa(Integer idPessoa, PessoaCreateDTO pessoaCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoa = buscarPessoaPorId(idPessoa);

        pessoa.setNome(pessoaCreateDTO.getNome());
        pessoa.setDataNascimento(pessoaCreateDTO.getDataNascimento());
        pessoaRepository.save(pessoa);

        return converterEmPessoaDTO(pessoa);
    }

    public PessoaDTO consultarUmaPessoa (Integer idPessoa) throws RegraDeNegocioException {
        return converterEmPessoaDTO(buscarPessoaPorId(idPessoa));
    }

    public PageDTO<PessoaDTO> listarPessoasComPaginacao (Integer idPessoa, String nomePessoa, LocalDate dataNascimento, Integer page, Integer size) throws RegraDeNegocioException {
        if (page < 0 || size < 0) {
            throw new RegraDeNegocioException("Page ou size não pode ser menor que zero");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PessoaEntity> pessoaEntities = pessoaRepository.listarPessoasComPaginacao(idPessoa, nomePessoa, dataNascimento, pageRequest);

        List<PessoaDTO> pessoasDTO = pessoaEntities.getContent()
                .stream()
                .map(this::converterEmPessoaDTO)
                .collect(Collectors.toList());

        if (pessoaEntities.isEmpty()) {
            throw new RegraDeNegocioException("Dados não encontrados.");
        }

        return new PageDTO<>(pessoaEntities.getTotalElements(),
                pessoaEntities.getTotalPages(),
                page,
                size,
                pessoasDTO);
    }

    public PessoaEntity buscarPessoaPorId (Integer idPessoa) throws RegraDeNegocioException {
        return pessoaRepository.findById(idPessoa).orElseThrow(() -> new RegraDeNegocioException("Não foi encontrado uma pessoa com o id " + idPessoa));
    }

    public PessoaEntity converterEmPessoaEntity (PessoaCreateDTO pessoa) {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setIdPessoa(pessoaEntity.getIdPessoa());
        pessoaEntity.setNome(pessoaEntity.getNome());
        pessoaEntity.setDataNascimento(pessoa.getDataNascimento());

        return pessoaEntity;
    }

    public PessoaDTO converterEmPessoaDTO (PessoaEntity pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(pessoa.getIdPessoa());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());

        return pessoaDTO;
    }
}
