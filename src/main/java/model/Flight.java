package model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
public class Flight {
    @Id
    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "DEPARTURE_TIME")
    private Calendar departureTime;

    @Column(name = "ARRIVAL_TIME")
    private Calendar arrivalTime;

    /*@Column(name = "SEAT_LEFT")
    private int seatsLeft;*/

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "FLI_PAS",
            joinColumns = {@JoinColumn(name = "FLI_NUM", referencedColumnName = "FLIGHT_NUMBER")},
            inverseJoinColumns = {@JoinColumn(name = "PAS_ID", referencedColumnName = "PASSENGER_ID")}
    )
    private List<Passenger> passengers;


    @ManyToMany(mappedBy = "flights")
    private List<Reservation> reservations;

    @Embedded
    private Plane plane;


    @JsonView(Views.Public.class)
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @JsonView(Views.Public.class)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonView(Views.Public.class)
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonView(Views.Public.class)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonView(Views.Public.class)
    public Calendar getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Calendar departureTime) {
        this.departureTime = departureTime;
    }

    @JsonView(Views.Public.class)
    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /*
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    */

    /*
    public int getSeatsLeft() {
        return seatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }
    */

    @JsonView(Views.Public.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(Views.Private3.class)
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @JsonView(Views.Public.class)
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }


}
