package fedor.lysenko.drone_service.drone.dto;

public class MedicationLoadRequest {

    private String droneSerialNumber;
    private String code;
    private Integer weight;
    private String name;

    public MedicationLoadRequest(String droneSerialNumber, String code, Integer weight, String name) {
        this.droneSerialNumber = droneSerialNumber;
        this.code = code;
        this.weight = weight;
        this.name = name;
    }

    public MedicationLoadRequest() {
    }

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
