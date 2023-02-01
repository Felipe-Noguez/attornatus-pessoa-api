package com.attornatus.pessoaapi.dto.pessoadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaDTO {

    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
}
