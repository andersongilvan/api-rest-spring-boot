package med.voll.api.domain.medico;

// Importa as anotações necessárias para validação de dados
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Importa o DTO de endereço
import med.voll.api.domain.endereco.DadosEndereco;

/**
 * Classe DTO (Data Transfer Object) para representar um médico.
 *
 * Esta classe é usada para transferir dados relacionados a um médico,
 * geralmente entre a camada de controle e a camada de serviço.
 *
 * A classe é uma `record`, o que significa que é uma classe imutável com
 * campos finais e gera automaticamente métodos como equals(), hashCode(),
 * e toString().
 */
public record DtoCadastroMedico(
        // Nome do médico.
        // Deve ser uma string não vazia.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        @NotBlank
        String nome,

        // Email do médico.
        // Deve ser uma string não vazia e formatada como um endereço de email válido.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        // A anotação @Email valida que o formato do email está correto.
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        // CRM (Conselho Regional de Medicina) do médico.
        // Deve ser uma string não vazia que corresponda a um padrão específico.
        // O padrão é uma sequência de 4 a 6 dígitos numéricos.
        // A anotação @NotBlank garante que o campo não será nulo nem vazio.
        // A anotação @Pattern valida que o campo segue o formato especificado.
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        // Especialidade do médico.
        // Não pode ser nulo. A anotação @NotNull garante que o campo deve ser fornecido.
        @NotNull
        Especialidade especialidade,

        // Endereço do médico.
        // Não pode ser nulo e deve ser validado.
        // A anotação @NotNull garante que o campo deve ser fornecido.
        // A anotação @Valid faz com que o Spring valide o objeto DtoEndereco de acordo com suas próprias anotações de validação.
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
