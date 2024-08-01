package med.voll.api.models.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.paciente.DtoCadastroPacientes;
import med.voll.api.domain.paciente.repository.DtoAtualizarPaciente;
import med.voll.api.models.endereco.Endereco;

import java.time.LocalDate;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String genero;

    @Column(name = "estado_civil")
    private String estadoCivil;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;

    public Paciente(DtoCadastroPacientes dados) {
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.genero = dados.genero();
        this.estadoCivil = dados.estadoCivil();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }
    public void atualizarInfo(DtoAtualizarPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInfo(dados.endereco());
        }
    }


}





 //Long id, String nome, String telefone, Endereco endereco