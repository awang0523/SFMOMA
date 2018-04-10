package model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name = "RESERVATION_NUM")
    private String reservationNumber;

    @Column(name = "PRICE")
    private double price; // sum of each flight’s price.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PASSENGER_ID")
    private Passenger passenger;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "RES_FLI",
            joinColumns = {@JoinColumn(name = "RES_NUM", referencedColumnName = "RESERVATION_NUM")},
            inverseJoinColumns = {@JoinColumn(name = "FLI_NUM", referencedColumnName = "FLIGHT_NUMBER")}
    )
    private List<Flight> flights;

    public Reservation() {
        this.reservationNumber = "reservation" + UUID.randomUUID().toString().replace("-", "");
    }

    @JsonView(Views.Public.class)
    public String getReservationNumber() {
        return reservationNumber;
    }

    @JsonView(Views.Public.class)
    public double getPrice(String reservationNumber) {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonView(Views.Private2.class)
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @JsonView(Views.Public.class)
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
