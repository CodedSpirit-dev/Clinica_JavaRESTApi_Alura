package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.DataMedicList;
import med.voll.api.medic.DataMedicRegister;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    @PostMapping
    public void registerMedic(@RequestBody @Valid DataMedicRegister dataMedicRegister) {
        medicRepository.save(new Medic(dataMedicRegister));
    }

    @GetMapping
    public Page<DataMedicList> medicList(@PageableDefault(size = 5)  Pageable pagination) {
        return medicRepository.findAll(pagination).map(DataMedicList::new);
    }
}