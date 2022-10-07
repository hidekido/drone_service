package fedor.lysenko.drone_service.drone.entity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drones")
public class Drone {
    @Id
    @Column(name = "serial_number", nullable=false, length = 100, unique = true)
    @NonNull
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model", nullable=false)
    @NonNull
    private DroneModel model;

    @Column(name = "weight_limit", nullable=false)
    @NonNull
    private Integer weightLimit;

    @Column(name = "battery_capacity")
    @NonNull
    private byte batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @Builder.Default
    private DroneState state = DroneState.IDLE;

    @Column(name = "weight_loaded")
    @Builder.Default
    private Integer weightLoaded = 0;
}
