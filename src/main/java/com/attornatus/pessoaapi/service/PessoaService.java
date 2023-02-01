package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public PessoaDTO editarPessoa(Integer idPessoa, PessoaCreateDTO pessoaCreateDTO) {
        PessoaEntity pessoa = buscarPessoaPorId(idPessoa);

        pessoa.setNome(pessoaCreateDTO.getNome());
        pessoa.setDataNascimento(pessoaCreateDTO.getDataNascimento());
        pessoaRepository.save(pessoa);

        return converterEmPessoaDTO(pessoa);
    }

    public PessoaEntity buscarPessoaPorId (Integer idPessoa){
        return pessoaRepository.findById(idPessoa).get();
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
