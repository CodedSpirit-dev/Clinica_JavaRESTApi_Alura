package med.voll.api.domain.medic;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findByActiveTrue(Pageable pagination);

    @Query("""
            select m from Medic m
            where m.active = true
            and
            m.speciality = :specialty
            and
            m.id not in(
                select c.medic.id from Consultation c
                where c.date = :localDateTime
            )
            order by rand()
            limit 1
            """)
    Medic selectMedicWithSpecialtyInDate(Speciality specialty, LocalDateTime localDateTime);

    @Query("""
select m.active
from Medic m
where m.id = :idMedic
""")
    Boolean findActiveById(Long idMedic);
}
