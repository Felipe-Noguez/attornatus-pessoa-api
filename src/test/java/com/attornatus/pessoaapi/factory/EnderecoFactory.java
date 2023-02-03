package com.attornatus.pessoaapi.factory;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.enums.TipoEndereco;

public class EnderecoFactory {

    public static EnderecoEntity getEnderecoEntity () {
        EnderecoEntity enderecoEntity = new EnderecoEntity();

        enderecoEntity.setIdEndereco(1);
        enderecoEntity.setIdPessoa(1);
        enderecoEntity.setLogradouro("Av. Ipriranga");
        enderecoEntity.setCep("98625-223");
        enderecoEntity.setNumero(2245);
        enderecoEntity.setCidade("Porto Alegre");
        enderecoEntity.setTipoEndereco(TipoEndereco.OUTRO);

        return enderecoEntity;
    }

    public static EnderecoDTO getEnderecoDTO () {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setIdEndereco(1);
        enderecoDTO.setIdPessoa(1);
        enderecoDTO.setLogradouro("Av. Ipriranga");
        enderecoDTO.setCep("98625-223");
        enderecoDTO.setNumero(2245);
        enderecoDTO.setCidade("Porto Alegre");
        enderecoDTO.setTipoEndereco(TipoEndereco.OUTRO);

        return enderecoDTO;
    }

    public static EnderecoCreateDTO getEnderecoCreateDTO () {
        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO();
        enderecoCreateDTO.setIdPessoa(1);
        enderecoCreateDTO.setLogradouro("Av. Ipriranga");
        enderecoCreateDTO.setCep("98625-223");
        enderecoCreateDTO.setNumero(2245);
        enderecoCreateDTO.setCidade("Porto Alegre");
        enderecoCreateDTO.setTipoEndereco(TipoEndereco.OUTRO);

        return enderecoCreateDTO;
    }

    public static EnderecoPessoaDTO getEnderecoPessoaDTO () {
        EnderecoPessoaDTO enderecoPessoaDTO = new EnderecoPessoaDTO();
        enderecoPessoaDTO.setIdEndereco(1);
        enderecoPessoaDTO.setIdPessoa(1);
        enderecoPessoaDTO.setNome("Neil Peart");
        enderecoPessoaDTO.setLogradouro("Av. Ipriranga");
        enderecoPessoaDTO.setCep("98625-223");
        enderecoPessoaDTO.setCidade("Porto Alegre");
        enderecoPessoaDTO.setNumero(2245);
        enderecoPessoaDTO.setTipoEndereco(TipoEndereco.OUTRO);

        return enderecoPessoaDTO;
    }
}
