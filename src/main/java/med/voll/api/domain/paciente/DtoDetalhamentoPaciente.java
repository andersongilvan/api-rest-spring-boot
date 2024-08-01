package med.voll.api.domain.paciente;

import med.voll.api.models.endereco.Endereco;
import med.voll.api.models.paciente.Paciente;

import java.time.LocalDate;

public record DtoDetalhamentoPaciente(Long id, String nome, LocalDate dataNascimento, String email, String genero,
                                      String estadoCivil, String telefone, Endereco endereco) {

    public DtoDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getEmail(), paciente.getGenero(),
                paciente.getEstadoCivil(), paciente.getTelefone(), paciente.getEndereco());
    }
}
   /*
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
    */