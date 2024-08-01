package med.voll.api.domain.paciente.repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DtoAtualizarPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
