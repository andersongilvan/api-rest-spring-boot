package med.voll.api.domain.medico;

import med.voll.api.models.endereco.Endereco;
import med.voll.api.models.medico.Medico;

public record DtoDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                    Especialidade especialidade,
                                    Endereco endereco) {

    public DtoDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
