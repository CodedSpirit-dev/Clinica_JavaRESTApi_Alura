package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
ResponseBody
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationAgendaService service;

    @PostMapping
    @Transactional
    public ResponseEntity makeAppointment(@RequestBody @Valid DataMakeAppointment dataMakeAppointment) {
        var response = service.makeAppointment(dataMakeAppointment);

        return ResponseEntity.ok(response);
    }
}
