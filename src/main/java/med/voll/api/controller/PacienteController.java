package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DtoCadastroPacientes;
import med.voll.api.domain.paciente.DtoDetalhamentoPaciente;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import med.voll.api.models.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
