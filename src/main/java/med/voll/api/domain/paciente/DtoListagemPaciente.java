package med.voll.api.domain.paciente;

import med.voll.api.models.medico.Medico;
import med.voll.api.models.paciente.Paciente;

import java.time.LocalDate;

public record DtoListagemPaciente(Long id, String nome, String telefone, LocalDate dataNascimento) {

    public DtoListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getDataNascimento());
    }
}
