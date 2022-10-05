package fedor.lysenko.drone_service.drone.entity;


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
    private String image;

    @GeneratedValue
    @Id
    private Long id;

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

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
