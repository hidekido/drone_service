package fedor.lysenko.drone_service.drone.dto;

import fedor.lysenko.drone_service.drone.entity.DroneModel;
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
public class DroneRegisterRequest {

    @NotBlank(message = "Serial number should not be blank")
    @NotNull(message = "Serial number should not be empty")
    @Size(min=1, max=100, message = "Serial number should be shorter or equal 100 characters")
    private String serialNumber;

    //todo might be good idea to handle enum mapping exceptions in Jackson Object mapper
    @NotNull(message = "Drone model should not be empty")
    private DroneModel model;

    @Max(value = 500, message = "Max value for field weightLimit is 500")
    @Min(value = 0, message = "Min value for field weightLimit is 0")
    @NotNull
    private Integer weightLimit;

}
