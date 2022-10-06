package fedor.lysenko.drone_service.drone.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {
    //todo возможно, нужно переназвать в DroneMedication

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "weight", nullable=false)
    private Integer weight;

    @Column(name = "code", nullable=false)
    private String code;

    @Column(name = "drone_serial_number", nullable=false)
    private String droneSerialNumber;

    @Lob
    @Column(name = "clob_image")
    private MultipartFile image;

    @GeneratedValue
    @Id
    private Long id;

    public Medication(String name, Integer weight, String code, String droneSerialNumber) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.droneSerialNumber = droneSerialNumber;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
