package med.voll.api.domain.paciente.repository;

import med.voll.api.models.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
