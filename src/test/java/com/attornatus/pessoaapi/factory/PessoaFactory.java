package com.attornatus.pessoaapi.factory;

import com.attornatus.pessoaapi.dto.pessoadto.PessoaCreateDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.entities.PessoaEntity;

import java.time.LocalDate;
import java.util.Set;

public class PessoaFactory {

    public static PessoaEntity getPessoaEntity () {
        PessoaEntity pessoaEntity = new PessoaEntity();
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        pessoaEntity.setIdPessoa(1);
        pessoaEntity.setNome("Neil Peart");
        pessoaEntity.setDataNascimento(LocalDate.of(1990, 02, 01));
        pessoaEntity.setEnderecos(Set.of(enderecoEntity));

        return pessoaEntity;
    }

    public static PessoaDTO getPessoaDTO () {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setIdPessoa(1);
        pessoaDTO.setNome("Neil Peart");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 02, 01));

        return pessoaDTO;
    }

    public static PessoaCreateDTO getPessoaCreateDTO () {
        PessoaCreateDTO pessoaCreateDTO = new PessoaCreateDTO();
        pessoaCreateDTO.setNome("Neil Peart");
        pessoaCreateDTO.setDataNascimento(LocalDate.of(1990, 02, 01));

        return pessoaCreateDTO;
    }
}
