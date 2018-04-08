package model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;

@Embeddable
public class Plane {
    private int capacity;

    private String model;

    private String manufacturer;

    private int year;

    @JsonView(Views.Public.class)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @JsonView(Views.Public.class)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @JsonView(Views.Public.class)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonView(Views.Public.class)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
