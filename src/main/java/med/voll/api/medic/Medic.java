package med.voll.api.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direction.Direction;

@Table(name = "medics")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String document;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Direction direction;

    public Medic(DataMedicRegister dataMedicRegister) {
        this.name = dataMedicRegister.name();
        this.email = dataMedicRegister.email();
        this.document = dataMedicRegister.document();
        this.speciality = dataMedicRegister.speciality();
        this.direction = new Direction(dataMedicRegister.direction());
    }
}
