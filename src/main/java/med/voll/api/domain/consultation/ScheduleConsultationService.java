package med.voll.api.domain.consultation;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.validations.ConsultationValidator;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.pacient.PacientRepository;
import med.voll.api.infra.errors.IntegrityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ScheduleConsultationService {

    @Autowired
    private PacientRepository pacientRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private List<ConsultationValidator> validators;

    public DataConsultationDetails schedule(@Valid DataScheduleConsultation data) {
        if (!pacientRepository.findById(data.idPacient()).isPresent()) {
            throw new IntegrityValidator("The pacient ID does not exist");
        }

        if (data.idMedic() != null && !medicRepository.existsById(data.idMedic())) {
            throw new IntegrityValidator("The medic ID does not exist");
        }

        validators.forEach(v -> v.validate(data));

        var pacient = pacientRepository.findById(data.idPacient()).get();
        var medic = selectMedic(data);

        if (medic == null) {
            throw new IntegrityValidator("There are no available medics");
        }

        var consultation = new Consultation(null, medic, pacient, data.dateTime());
        consultationRepository.save(consultation);
        return new DataConsultationDetails(consultation);
    }

    private Medic selectMedic(DataScheduleConsultation data) {
        if (data.idMedic() != null) {
            return medicRepository.getReferenceById(data.idMedic());
        }

        if (data.specialty() == null) {
            throw new IntegrityValidator("Specialty is required");
        }
        return medicRepository.selectMedicWithSpecialtyInDate(data.specialty(), data.dateTime());
    }
}
