package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FlightTest {
    Flight flight;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {
        flight = new Flight();
    }

    @Test
    public void getFlightNumber() {
        String fn = "12345";
        flight.setFlightNumber(fn);

        assertEquals(fn, flight.getFlightNumber());
    }

    @Test
    public void getPrice() {
        double price = 1000.00;
        flight.setPrice(price);

        assertEquals(price, flight.getPrice(),DELTA);

    }

    @Test
    public void getOrigin() {
        String origin = "San Diego";
        flight.setOrigin(origin);

        assertEquals(origin,flight.getOrigin());

    }

    @Test
    public void getDestination() {
        String destination = "New York";
        flight.setDestination(destination);

        assertEquals(destination, flight.getDestination());
    }

    @Test
    public void getDepartureTime() {
        Date date = new Date();
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        flight.setDepartureTime(dt);

        assertEquals(dt, flight.getDepartureTime());
    }

    @Test
    public void getArrivalTime() {
        Date date = new Date();
        Calendar at = Calendar.getInstance();
        at.setTime(date);
        flight.setArrivalTime(at);

        assertEquals(at, flight.getArrivalTime());
    }

    @Test
    public void getDescription() {
        String description = "Express Line";
        flight.setDescription(description);

        assertEquals(description,flight.getDescription());
    }

    @Test
    public void getPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        Passenger p1 = new Passenger();
        Passenger p2 = new Passenger();
        passengers.add(p1);
        passengers.add(p2);
        flight.setPassengers(passengers);

        assertEquals(passengers,flight.getPassengers());
    }

    @Test
    public void getPlane() {
        Plane plane = new Plane();
        flight.setPlane(plane);

        assertEquals(plane, flight.getPlane());
    }
}