package com.attornatus.pessoaapi.dto.pessoadto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaCreateDTO {

    private String nome;
    private LocalDate dataNascimento;
}
