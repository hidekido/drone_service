package fedor.lysenko.drone_service.drone.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medication")
public class Medication {
    @Column(name = "name", nullable=false)
    @NonNull
    private String name;

    @Column(name = "weight", nullable=false)
    @NonNull
    private Integer weight;

    @Column(name = "code", nullable=false)
    @NonNull
    private String code;

    @Column(name = "drone_serial_number", nullable=false)
    @NonNull
    private String droneSerialNumber;

    @Lob
    @Column(name = "image")
    @NonNull
    private String image;

    @GeneratedValue
    @Id
    private Long id;
}
