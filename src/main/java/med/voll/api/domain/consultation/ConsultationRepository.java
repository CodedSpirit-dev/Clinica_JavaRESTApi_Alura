package med.voll.api.domain.consultation;

import med.voll.api.domain.pacient.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationRepository extends  JpaRepository<Consultation, Long> {
    Boolean existsByPacientAndDateTimeBetween(Long pacient, LocalDateTime firstDateTime, LocalDateTime lastDateTime);

    Boolean existByMedicIdAndDateTime(Long medicId, LocalDateTime date);
}
