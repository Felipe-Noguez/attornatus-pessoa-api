package com.attornatus.pessoaapi.repository;

import com.attornatus.pessoaapi.entities.PessoaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {

    @Query( " select distinct obj " +
            " from pessoa obj " +
            " where (:idPessoa is null or :idPessoa = obj.idPessoa ) " +
            " and (:nomePessoa is null  or upper(obj.nome) like upper(concat('%', :nomePessoa, '%') ) ) " +
            " and (:dataNascimento is null or :dataNascimento = obj.dataNascimento ) " +
            "")
    Page<PessoaEntity> listarPessoasComPaginacao(Integer idPessoa, String nomePessoa, LocalDate dataNascimento, PageRequest pageRequest);
}
