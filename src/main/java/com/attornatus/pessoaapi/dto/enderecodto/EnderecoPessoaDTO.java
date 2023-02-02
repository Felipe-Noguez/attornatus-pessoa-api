package com.attornatus.pessoaapi.dto.enderecodto;

import com.attornatus.pessoaapi.enums.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoPessoaDTO {

    private Integer idEndereco;
    private Integer idPessoa;
    private String nome;
    private String logradouro;
    private String cep;
    private String cidade;
    private Integer numero;
    private TipoEndereco tipoEndereco;
}
