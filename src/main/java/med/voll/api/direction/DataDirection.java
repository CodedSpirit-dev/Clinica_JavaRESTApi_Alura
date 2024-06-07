package med.voll.api.direction;

import jakarta.validation.constraints.NotBlank;

public record DataDirection(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement
) {
}
