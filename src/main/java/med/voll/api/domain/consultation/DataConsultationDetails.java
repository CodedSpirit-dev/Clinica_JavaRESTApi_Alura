package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record DataConsultationDetails(
        Long id,
        Long idPacient,
        Long idMedic,
        LocalDateTime dateTime
) {
    public DataConsultationDetails(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getPacient().getId(),
                consultation.getMedic().getId(),
                consultation.getDate()
        );
    }
}
