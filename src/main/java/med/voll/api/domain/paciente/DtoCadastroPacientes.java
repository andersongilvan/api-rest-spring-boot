package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

import java.time.LocalDate;

public record DtoCadastroPacientes(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        String genero,
        @NotBlank
        String estadoCivil,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}

















