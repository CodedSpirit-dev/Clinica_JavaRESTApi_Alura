package med.voll.api.domain.consultation.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataScheduleConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicsWithConsultation implements ConsultationValidator{

    @Autowired
    private ConsultationRepository repository;

    public void validate(DataScheduleConsultation data) {
        if(data.idMedic() == null)
            return;

        var medicWithConsultation = repository.existsByMedicIdAndDate(data.idMedic(), data.dateTime());

        if(medicWithConsultation)
            throw new ValidationException("The medic has an appointment in this time");
    }
}
