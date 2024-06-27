package med.voll.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationRepository extends  JpaRepository<Consultation, Long> {
    Boolean existsByPacientIdAndDateTimeBetween(Long pacientId, LocalDateTime firstDateTime, LocalDateTime lastDateTime);

    Boolean existByMedicIdAndDateTime(Long medicId, LocalDateTime date);
}
