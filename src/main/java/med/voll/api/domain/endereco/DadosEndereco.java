package med.voll.api.domain.endereco;


// Importa as anotações necessárias para validação de dados

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Classe DTO (Data Transfer Object) para representar um endereço.
 * <p>
 * Esta classe é usada para transferir dados relacionados a um endereço,
 * geralmente entre a camada de controle e a camada de serviço.
 * <p>
 * A classe é uma `record`, o que significa que é uma classe imutável com
 * campos finais e gera automaticamente métodos como equals(), hashCode(),
 * e toString().
 */
public record DadosEndereco(
        // Logradouro do endereço.
        // Deve ser uma string não vazia.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        @NotBlank
        String logradouro,

        // Bairro do endereço.
        // Deve ser uma string não vazia.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        @NotBlank
        String bairro,

        // CEP do endereço.
        // Deve ser uma string não vazia e corresponder a um padrão específico.
        // O padrão é uma sequência de 8 dígitos numéricos.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        // A anotação @Pattern valida que o campo segue o formato especificado.
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        // Cidade do endereço.
        // Deve ser uma string não vazia.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        @NotBlank
        String cidade,

        // Unidade Federativa (UF) do endereço.
        // Deve ser uma string não vazia.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        @NotBlank
        String uf,

        // Complemento do endereço.
        // Este campo é opcional, portanto não possui validações adicionais.
        String complemento,

        // Número do endereço.
        // Este campo é opcional, portanto não possui validações adicionais.
        String numero
) {
}
