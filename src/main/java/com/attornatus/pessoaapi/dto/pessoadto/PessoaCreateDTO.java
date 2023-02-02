package com.attornatus.pessoaapi.dto.pessoadto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaCreateDTO {

    @NotBlank(message = "Campo obrigatório, não pode estar em branco")
    @Schema(description = "Nome da pessoa", example = "Neil Peart")
    private String nome;
    @NotNull(message = "Campo obrigatório, não pode estar em branco")
    @Schema(description = "Data de nascimento da pessoa", example = "2000-01-01")
    private LocalDate dataNascimento;
}
