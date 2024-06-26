package med.voll.api.domain.pacient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direction.Direction;


@Table(name = "pacients")
@Entity(name = "Pacient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String document;
    @Embedded
    private Direction direction;
    private Boolean active;

    public Pacient(DataPacientRegister dataPacient) {
        this.name = dataPacient.name();
        this.email = dataPacient.email();
        this.phoneNumber = dataPacient.phoneNumber();
        this.document = dataPacient.document();
        this.direction = new Direction(dataPacient.direction());
        this.active = true;
    }

    public void updateData(DataPacientUpdate dataPacientUpdate) {
        if (dataPacientUpdate.name() != null) {
            this.name = dataPacientUpdate.name();
        }
        if (dataPacientUpdate.phoneNumber() != null) {
            this.phoneNumber = dataPacientUpdate.phoneNumber();
        }
        if (dataPacientUpdate.direction() != null) {
            this.direction = new Direction(dataPacientUpdate.direction());
        }
    }

    public void disable() {
        this.active = false;
    }
}
