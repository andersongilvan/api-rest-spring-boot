package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DtoCadastroPacientes;
import med.voll.api.domain.paciente.DtoDetalhamentoPaciente;
import med.voll.api.domain.paciente.DtoListagemPaciente;
import med.voll.api.domain.paciente.repository.DtoAtualizarPaciente;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import med.voll.api.models.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastratr(@RequestBody @Valid DtoCadastroPacientes dados, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var idPaciente = paciente.getId();
        var uri = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(idPaciente).toUri();
        var dto = new DtoDetalhamentoPaciente(paciente);
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity <Page<DtoListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DtoListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity pacienteId(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        var dto = new DtoDetalhamentoPaciente(paciente);

        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody DtoAtualizarPaciente dados) {
        var idPaciente = dados.id();
        var paciente = repository.getReferenceById(idPaciente);
        paciente.atualizarInfo(dados);
        var dto = new DtoDetalhamentoPaciente(paciente);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
          repository.deleteById(id);

          return ResponseEntity.noContent().build();
    }
}
