package med.voll.api.domain.pacient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Page<Pacient> findByActiveTrue(Pageable pagination);

    @Query("""
            select p.active
            from Pacient p
            where p.id = :idPacient
            """)
    Boolean findActiveById(Long idPacient);
}
