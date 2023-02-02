package com.attornatus.pessoaapi.repository;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.enums.TipoEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

    @Query( "select distinct new com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO ( " +
            " en.idEndereco," +
            " en.idPessoa, " +
            " en.pessoa.nome, " +
            " en.logradouro, " +
            " en.cep," +
            " en.cidade, " +
            " en.numero, " +
            " en.tipoEndereco " +
            " ) " +
            " from endereco en " +
            " inner join en.pessoa ep " +
            " on (:idEndereco is null or :idEndereco = en.idEndereco ) " +
            " and (:idPessoa is null or :idPessoa = en.idPessoa) " +
            " and (:nomePessoa is null  or upper(en.pessoa.nome) like upper(concat('%', :nomePessoa, '%') ) ) " +
            " and (:logradouro is null  or upper(en.logradouro) like upper(concat('%', :logradouro, '%') ) ) " +
            " and (:cep is null  or en.cep like concat('%', :cep, '%') ) " +
            " and (:cidade is null  or upper(en.cidade) like upper(concat('%', :cidade, '%') ) ) " +
            " and (:tipoEndereco is null or :tipoEndereco = en.tipoEndereco) " +
            "")
    Page<EnderecoPessoaDTO> listarEnderecosPaginado(Integer idEndereco, Integer idPessoa, String nomePessoa, String logradouro, String cep, String cidade, TipoEndereco tipoEndereco, PageRequest pageRequest);
}
