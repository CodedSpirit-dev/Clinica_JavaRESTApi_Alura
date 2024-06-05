package med.voll.api.controller;

import med.voll.api.medic.DataMedicRegister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicoController {

    @PostMapping
    public void registerMedic(@RequestBody DataMedicRegister dataMedicRegister) {
        System.out.println("Medico registrado");
        System.out.println(dataMedicRegister);
    }
}
