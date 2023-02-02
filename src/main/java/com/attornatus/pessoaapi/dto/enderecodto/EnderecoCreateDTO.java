package com.attornatus.pessoaapi.dto.enderecodto;

import com.attornatus.pessoaapi.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoCreateDTO {

    @Schema(description = "Id da pessoa para cadastrar o endereço", example = "4")
    @Min(message = "Campo obrigatório, não pode ser menor que 1", value = 1)
    private Integer idPessoa;
    @NotBlank(message = "Campo obrigatório, não pode estar em branco")
    @Schema(description = "Campo com endereço", example = "Av. Bento Gonçalves")
    private String logradouro;
    @NotBlank(message = "Campo obrigatório, não pode estar em branco")
    @Schema(description = "Campo de cep", example = "98622-123")
    private String cep;
    @Min(message = "Campo obrigatório, não pode ser menor que 1", value = 1)
    @Schema(description = "Campo de número do imóvel", example = "897")
    private Integer numero;
    @NotBlank(message = "Campo obrigatório, não pode estar em branco")
    @Schema(description = "Campo com nome da cidade", example = "Porto Alegre")
    private String cidade;
    @Schema(description = "Campo com tipo de endereço", example = "PRINCIPAL")
    private TipoEndereco tipoEndereco;
}
