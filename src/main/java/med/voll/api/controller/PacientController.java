package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.pacient.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/pacients")
public class PacientController {

    @Autowired
    private PacientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPacient(@RequestBody @Valid DataPacientRegister dataPacient, UriComponentsBuilder uriComponentsBuilder) {
        var pacient = new Pacient(dataPacient);
        repository.save(pacient);
        var uri = uriComponentsBuilder.path("/pacients/{id}").buildAndExpand(pacient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataPacient(pacient));
    }

    @GetMapping
    public ResponseEntity<Page<DataPacientList>> listPacients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(DataListPacient::new);
        return ResponseEntity.ok(page);
    }

    }

    @GetMapping
    @Transactional
    public ResponseEntity updatePacient(@RequestBody @Valid DataPacientUpdate dataPacient) {
        var pacient = repository.getReferenceById(id);
        pacient.updateData(dataPacient);
        return ResponseEntity.ok(new DataDetailsPacient(pacient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePacient(@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);
        pacient.disable();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detailsPacient(@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsPacient(pacient));
    }
}
