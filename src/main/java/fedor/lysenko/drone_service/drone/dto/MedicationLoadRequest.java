package fedor.lysenko.drone_service.drone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class MedicationLoadRequest {

    @NotBlank(message = "Serial number should not be blank")
    @NotNull(message = "Serial number should not be null")
    @Size(min=1, max=100)
    private String droneSerialNumber;

    @NotBlank(message = "Medication code should not be blank")
    @NotNull(message = "Medication code should not be null")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Medication code allowed only upper case letters, underscore and numbers!")
    private String code;

    @NotNull(message = "Medication code should not be null")
    @Min(value = 1, message = "Medication weight should be at least 1g")
    @Max(value = 500, message = "Max value for field weight is 500")
    private Integer weight;

    @NotBlank(message = "Medication name should not be blank")
    @NotNull(message = "Medication name should not be null")
    @Pattern(regexp = "^[\\da-zA-Z\\-_]+$", message = "Medication name allowed only letters, numbers, ‘-‘, ‘_’!")
    private String name;

    @NotBlank(message = "Medication image should not be blank")
    @NotNull(message = "Medication image should not be null")
    private String image;
}
