package med.voll.api.controller;

import med.voll.api.medic.DataMedicRegister;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository medicRepository;

    @PostMapping
    public void registerMedic(@RequestBody DataMedicRegister dataMedicRegister) {
        medicRepository.save(new Medic(dataMedicRegister));
    }
}
