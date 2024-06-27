package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataScheduleConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientsWithoutConsultation implements ConsultationValidator{

    @Autowired
    private ConsultationRepository repository;

    public void validate(DataScheduleConsultation data) {
        var firstDateTime = data.dateTime().withHour(7);
        var lastDateTime = data.dateTime().withHour(18);

        var pacientWithConsultation = repository.existsByPacientIdAndDateBetween(data.idPacient(), firstDateTime, lastDateTime);

        if (pacientWithConsultation) {
            throw new ValidationException("The pacient has an appointment in this time");
        }
    }
}
