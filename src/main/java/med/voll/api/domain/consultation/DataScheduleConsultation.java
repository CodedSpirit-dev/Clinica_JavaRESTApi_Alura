package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medic.Speciality;

import java.time.LocalDateTime;

public record DataScheduleConsultation(
        @NotNull
        Long idPacient,
        Long idMedic,
        @NotNull
        @Future
        LocalDateTime dateTime,
        Speciality specialty
) {
}
