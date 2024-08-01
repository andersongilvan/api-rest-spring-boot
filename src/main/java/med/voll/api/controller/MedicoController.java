package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.models.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    // A anotação @Autowired é usada para permitir a injeção automática de dependências
    // O Spring Framework vai automaticamente inicializar o campo 'repository' com uma instância do MedicoRepository
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DtoCadastroMedico dados) {

        var medico = new Medico(dados);
        repository.save(medico);
    }

  /*  // Listar todos os regfistros do banco de dados
    @GetMapping
    public List<DadosListagenMedico> listar() {
        return repository.findAll().stream().map(DadosListagenMedico::new).toList();
    }    */

    @GetMapping
    public Page<DadosListagenMedico>  listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagenMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody DtoAtualizacao dados) {
        var id = dados.id();
        var medico = repository.getReferenceById(id);
        medico.atualizarInfo(dados);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deletarBYId(@PathVariable Long id) {
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
