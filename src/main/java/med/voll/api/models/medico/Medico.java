package med.voll.api.models.medico;

// Importa as anotações e classes necessárias para persistência de dados

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.DtoAtualizacao;
import med.voll.api.domain.medico.DtoCadastroMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.models.endereco.Endereco;

// Define que esta classe é mapeada para a tabela "medicos" no banco de dados
@Table(name = "medicos")
// Define que esta classe é uma entidade JPA
@Entity(name = "Medico")
// Anotação Lombok para gerar automaticamente os getters para todos os campos
@Getter
// Anotação Lombok para gerar automaticamente um construtor sem argumentos
@NoArgsConstructor
// Anotação Lombok para gerar automaticamente um construtor com argumentos para todos os campos
@AllArgsConstructor
// Anotação Lombok para gerar automaticamente os métodos equals() e hashCode() usando o campo "id"
@EqualsAndHashCode(of = "id")
public class Medico {
    // Define o campo 'id' como a chave primária da tabela
    @Id
    // Configura a geração automática do valor do 'id' pela estratégia de auto incremento
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public boolean getAtivo() {
        return ativo;
    }

    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private String crm;
    // Indica que o campo 'endereco' é um objeto embutido e seus campos serão mapeados como colunas na tabela
    @Embedded
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Medico(DtoCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }


    public void atualizarInfo(DtoAtualizacao dados) {
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
    public void desativar() {
        this.ativo = false;
    }
}


