package com.attornatus.pessoaapi.dto.enderecodto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoDTO {

    private Integer idEndereco;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
}
