package med.voll.api.domain.direction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {
    private String street;
    private String district;
    private String city;
    private String number;
    private String complement;

    public Direction(DataDirection direction) {
        this.street = direction.street();
        this.district = direction.district();
        this.city = direction.city();
        this.number = direction.number();
        this.complement = direction.complement();
    }

    public Direction updateData(DataDirection direction) {
        this.street = direction.street();
        this.district = direction.district();
        this.city = direction.city();
        this.number = direction.number();
        this.complement = direction.complement();
        return this;
    }
}
