package med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicoController {

    @PostMapping
    public void registerMedic(@RequestBody String parameter) {
        System.out.println("Medico registrado");
        System.out.println(parameter);
    }
}
