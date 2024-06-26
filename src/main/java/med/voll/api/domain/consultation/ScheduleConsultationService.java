package med.voll.api.domain.consultation;

import med.voll.api.domain.consultation.validations.ConsultationValidator;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.pacient.PacientRepository;
import med.voll.api.infra.exceptions.IntegrityValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleConsultationService {

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private List<ConsultationValidator> consultationValidators;

    public DataConsultationDetail schedule(DataScheduleConsultation data) {
        if (!pacientRepository.findById(data.getPacientId()).isPresent()) {
            throw new IntegrityValidation("The pacient ID does not exist");
        }
    }
}
