package med.voll.api.domain.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direction.Direction;

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
    private String phoneNumber;
    private String document;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Direction direction;

    public Medic(DataMedicRegister dataMedicRegister) {
        this.name = dataMedicRegister.name();
        this.email = dataMedicRegister.email();
        this.phoneNumber = dataMedicRegister.phoneNumber();
        this.document = dataMedicRegister.document();
        this.active = true;
        this.speciality = dataMedicRegister.speciality();
        this.direction = new Direction(dataMedicRegister.direction());
    }

    public void updateData(DataMedicUpdate dataMedicUpdate) {
        if (dataMedicUpdate.name() != null) {
            this.name = dataMedicUpdate.name();
        }
        if (dataMedicUpdate.document() != null) {
            this.document = dataMedicUpdate.document();
        }
        if (dataMedicUpdate.direction() != null) {
            this.direction = direction.updateData(dataMedicUpdate.direction());
        }
    }

    public void disableMedic(){
        this.active = false;
    }
}
