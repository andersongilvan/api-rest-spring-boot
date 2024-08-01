package med.voll.api.domain.medico.repository;

// Importa a interface JpaRepository do Spring Data JPA
import med.voll.api.models.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

// Define uma interface de repositório para a entidade Medico
// A interface MedicoRepository herda de JpaRepository, o que proporciona vários métodos para operações de CRUD
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Aqui, JpaRepository<Medico, Long> indica que o repositório trabalha com a entidade Medico
    // e o tipo da chave primária (id) é Long.
}
