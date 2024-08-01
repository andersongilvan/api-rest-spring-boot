package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.repository.MedicoRepository;
import med.voll.api.models.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    // A anotação @Autowired é usada para permitir a injeção automática de dependências
    // O Spring Framework vai automaticamente inicializar o campo 'repository' com uma instância do MedicoRepository
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        repository.save(medico);
        var medicoId = medico.getId();
        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medicoId).toUri();
        var dto = new DtoDetalhamentoMedico(medico);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity medicoId(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        var dto = new DtoDetalhamentoMedico(medico);

        return ResponseEntity.ok(dto);
    }

  /*  // Listar todos os regfistros do banco de dados
    @GetMapping
    public List<DadosListagenMedico> listar() {
        return repository.findAll().stream().map(DadosListagenMedico::new).toList();
    }    */

    @GetMapping
    public ResponseEntity<Page<DadosListagenMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagenMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DtoAtualizacao dados) {
        var id = dados.id();
        var medico = repository.getReferenceById(id);
        medico.atualizarInfo(dados);
        var dto = new DtoDetalhamentoMedico(medico);
        return ResponseEntity.ok(dto);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deletarBYId(@PathVariable Long id) {
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }
}
