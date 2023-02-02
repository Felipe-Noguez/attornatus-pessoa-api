package com.attornatus.pessoaapi.dto.enderecodto;

import com.attornatus.pessoaapi.enums.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDTO {

    private Integer idEndereco;
    private Integer idPessoa;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private TipoEndereco tipoEndereco;
}
