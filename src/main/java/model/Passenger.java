package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PASSENGER")
public class Passenger { // resource representation class

    @Id
    @Column(name = "PASSENGER_ID")
    private String passengerId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private int age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE")
    private String phone; // Phone numbers must be unique

    @OneToMany(mappedBy = "passenger")
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "passengers")
    private List<Flight> flights;

    public Passenger() {
        this.passengerId = "passenger" + UUID.randomUUID().toString().replace("-", "");
    }

    @JsonView(Views.Public.class)
    @JsonProperty("id")
    public String getPassengerId() {
        return passengerId;
    }

    @JsonView(Views.Public.class)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonView(Views.Public.class)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonView(Views.Public.class)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonView(Views.Public.class)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonView(Views.Public.class)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonView(Views.Private1.class)
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    /*
    public List<Flight> getFlight() {
        return flights;
    }

    public void setFlight(List<Flight> flights) {
        this.flights = flights;
    }
    */
}

