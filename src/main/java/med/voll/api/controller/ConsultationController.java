package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.DataScheduleConsultation;
import med.voll.api.domain.consultation.ScheduleConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consultation")
@ResponseBody
public class ConsultationController {

    @Autowired
    private ScheduleConsultationService service;

    @PostMapping
    @Transactional
    public ResponseEntity makeAppointment(@RequestBody @Valid DataScheduleConsultation dataScheduleConsultation) {
        var response = service.schedule(dataScheduleConsultation);

        return ResponseEntity.ok(response);
    }
}