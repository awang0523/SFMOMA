package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PassengerTest {
    Passenger passenger;

    @Before
    public void setUp() throws Exception {
        passenger = new Passenger();
    }

    @Test
    public void getFirstName() {
        String fn = "Jack";
        passenger.setFirstName(fn);

        assertEquals(fn, passenger.getFirstName());
    }

    @Test
    public void getLastName() {
        String ln = "Smith";
        passenger.setLastName(ln);

        assertEquals(ln, passenger.getLastName());
    }

    @Test
    public void getAge() {
        int age = 22;
        passenger.setAge(age);

        assertEquals(age, passenger.getAge());
    }

    @Test
    public void getGender() {
        String gender = "Male";
        passenger.setGender(gender);

        assertEquals(gender, passenger.getGender());
    }

    @Test
    public void getPhone() {
        String phone = "1234567890";
        passenger.setPhone(phone);

        assertEquals(phone, passenger.getPhone());
    }

    @Test
    public void getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation r1 = new Reservation();
        Reservation r2 = new Reservation();
        reservations.add(r1);
        reservations.add(r2);
        passenger.setReservations(reservations);

        assertEquals(reservations, passenger.getReservations());

    }
}