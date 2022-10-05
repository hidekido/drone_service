package fedor.lysenko.drone_service.drone.entity;


import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;

@Entity
@Table(name = "drones")
public class Drone {
    @Id
    @Column(name = "serial_number", nullable=false, length = 100, unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model", nullable=false)
    private DroneModel model;

    @Column(name = "weight_limit", nullable=false)
    private Integer weightLimit;

    @Column(name = "battery_capacity")
    private byte batteryCapacity = 100;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state = DroneState.IDLE;

    @Column(name = "weight_loaded")
    private Integer weightLoaded = 0;

    public Drone(String serialNumber, DroneModel model, int weightLimit, byte batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone() {
        System.out.println("Drone constructor");
    }


    public void setBatteryCapacity(byte batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public byte getBatteryCapacity() {
        return batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public Integer getWeightLoaded() {
        return weightLoaded;
    }
}
