package med.voll.api.domain.medico;

import med.voll.api.models.medico.Medico;

public record DadosListagenMedico
        (Long id, String nome, String email, String crm, Especialidade especialidade, Boolean ativo) {

        public DadosListagenMedico(Medico medico) {
                this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
                        medico.getEspecialidade(), medico.getAtivo());
        }
}
