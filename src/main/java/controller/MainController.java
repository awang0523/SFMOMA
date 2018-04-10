package controller;

import com.fasterxml.jackson.annotation.JsonView;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public class MainController {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ResToFliRepository resToFliRepository;

    @Autowired
    private FliToPasRepository fliToPasRepository;



    // Map passenger
    @JsonView(Views.Private1.class)
    @GetMapping(path = "/passenger/{passengerid}")
    public @ResponseBody
    Passenger getPassenger(@PathVariable("passengerid") String id) {
        Passenger passenger = passengerRepository.findPassengerByPassengerId(id);
        if (passenger == null) {
            throw new NotFoundException(
                    "Sorry, the requested passenger with id " + id + " does not exist" );
        }
        else {
            return passengerRepository.findPassengerByPassengerId(id);
        }
    }

    @PostMapping(path = "/passenger") // Map Only POST request
    // Create a new passenger
    public @ResponseBody Passenger addPassenger (
            @RequestParam String firstname
            , @RequestParam String lastname
            , @RequestParam int age
            , @RequestParam String gender
            , @RequestParam String phone
    ) {

        Passenger passenger = new Passenger();
        passenger.setFirstName(firstname);
        passenger.setLastName(lastname);
        passenger.setAge(age);
        passenger.setGender(gender);
        passenger.setPhone(phone);
        passengerRepository.save(passenger);
        return passengerRepository.findPassengerByPassengerId(passenger.getPassengerId());
    }

    @JsonView(Views.Private1.class)
    @PutMapping(path = "/passenger/{passengerid}")
    public @ResponseBody
    Passenger updatePassenger(
            @PathVariable("passengerid") String passengerid
            , @RequestParam String firstname
            , @RequestParam String lastname
            , @RequestParam int age
            , @RequestParam String gender
            , @RequestParam String phone) {
        Passenger passenger = passengerRepository.findPassengerByPassengerId(passengerid);
        if (passenger == null) {
            throw new NotFoundException(
                    "Sorry, the requested passenger with id " + passengerid + " does not exist" );
        }
        else {
            passenger.setFirstName(firstname);
            passenger.setLastName(lastname);
            passenger.setAge(age);
            passenger.setGender(gender);
            passenger.setPhone(phone);
            return passengerRepository.findPassengerByPassengerId(passengerid);
        }
    }

    // Delete passenger
    @DeleteMapping(path = "passenger/{passengerid}")
    public @ResponseBody String deletePassenger(@PathVariable("passengerid") String passengerid) {
        Passenger passenger = passengerRepository.findPassengerByPassengerId(passengerid);
        if (passenger == null) {
            throw new NotFoundException(passengerid);
        }
        else {
            passengerRepository.deletePassengerByPassengerId(passengerid);
            return "Delete Successfully.";
        }
    }



    // Map reservation
    @JsonView(Views.Private2.class)
    @GetMapping(path = "/reservation/{reservationnum}")
    public @ResponseBody Optional<Reservation> getReservation(@PathVariable("reservationnum") String number) {

        Optional<Reservation> reservation = reservationRepository.findById(number);
        if (!reservation.isPresent()) {
            throw new NotFoundException(
                    "Reserveration with number " + number + " does not exist");
        }
        else {
            return reservationRepository.findById(number);
        }
    }

    @PostMapping(path = "/reservation")
    public @ResponseBody Optional<Reservation> addReservation(
            @RequestParam String passengerId
            , @RequestParam String flightLists
    ) {
        Reservation reservation = new Reservation();
        Passenger passenger = passengerRepository.findPassengerByPassengerId(passengerId);
        reservation.setPassenger(passenger);
        List<Flight> flights = reservation.getFlights();
        double totalPrice = 0.0;
        for (Flight flight : flights) {
            totalPrice += flight.getPrice();
            flight.setSeatsLeft(flight.getPlane().getCapacity() - 1);
        }
        reservation.setPrice(totalPrice);
        reservationRepository.save(reservation);


        ResToFli resToFli = new ResToFli();
        String[] flightLists1 = flightLists.split(",");
        for (int i = 0; i < flightLists1.length; i++) {
            resToFli.setFlightNumber(flightLists1[i]);
            resToFli.setReservationNumber(reservation.getReservationNumber());
        }
        resToFliRepository.save(resToFli);

        FliToPas fliToPas = new FliToPas();
        for (int i = 0; i < flightLists1.length; i++) {
            fliToPas.setFlightNumber(flightLists1[i]);
            fliToPas.setPassengerId(passengerId);
        }
        fliToPasRepository.save(fliToPas);
        return reservationRepository.findById(reservation.getReservationNumber());
    }


    // Update reservation
    @PutMapping(path = "/reservation/{reservationnum}")
    public @ResponseBody Optional<Reservation> updateReservation(
            @PathVariable("reservationnum") String number
            , @RequestParam String flightAdded
            , @RequestParam String flightRemoved) {
        Reservation reservation = reservationRepository.findReservationByReservationNumber(number);

        List<Flight> flights = reservation.getFlights();
        ResToFli resToFli = new ResToFli();

        String[] removedFlights = flightRemoved.split(",");
        String[] addedFlights = flightAdded.split(",");

        for (int i = 0; i < removedFlights.length; i++) {
            resToFliRepository.deleteResToFliByFlightNumber(removedFlights[i]);

        }

        for (int i = 0; i< addedFlights.length; i++) {
            resToFli.setReservationNumber(number);
            resToFli.setFlightNumber(addedFlights[i]);
        }

        resToFliRepository.save(resToFli);


        return reservationRepository.findById(number);

    }

    // search reservation
    // @GetMapping

    // Delete reservation
    @DeleteMapping(path = "/reservation/{reservationnum}")
    public @ResponseBody String deleteReservation(@PathVariable("reservationnum") String reservationnum) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationnum);
        if (!reservation.isPresent()) {
            throw new NotFoundException(reservationnum);
        }
        else {
            flightRepository.deleteById(reservationnum);
            return "Delete Successfully.";
        }
    }



    // Map flight
    @JsonView(Views.Private3.class)
    @GetMapping(path = "/flight/{flightnum}")
    public @ResponseBody Optional<Flight> getFlight(@PathVariable("flightnum") String flightnum) {
        Optional<Flight> flight = flightRepository.findById(flightnum);
        if (!flight.isPresent()) {
            throw new NotFoundException(
                    "Sorry, the requested flight with number " + flightnum + " does not exist");
        }
        return flightRepository.findById(flightnum);
    }

    @PostMapping(path = "/flight/{flightnumber}")
    public @ResponseBody Optional<Flight> addFlight (
            @PathVariable("flightnumber") String flightnumber
            , @RequestParam double price
            , @RequestParam String origin
            , @RequestParam String destination
            , @RequestParam int departuretime
            , @RequestParam int arrivaltime
            , @RequestParam String description
            , @RequestParam int capacity
            , @RequestParam String model
            , @RequestParam String manufacturer
            , @RequestParam int year) {


        Flight flight = new Flight();
        flight.setFlightNumber(flightnumber);
        flight.setPrice(price);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDescription(description);
        flight.setSeatsLeft(capacity);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, departuretime / 1000000 % 10000);
        cal1.set(Calendar.MONTH, departuretime / 10000 % 100);
        cal1.set(Calendar.DAY_OF_MONTH, departuretime / 100 % 100);
        cal1.set(Calendar.HOUR_OF_DAY, departuretime % 100);
        flight.setDepartureTime(cal1);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, arrivaltime / 1000000 % 10000);
        cal2.set(Calendar.MONTH, arrivaltime / 10000 % 100);
        cal2.set(Calendar.DAY_OF_MONTH, arrivaltime / 100 % 100);
        cal2.set(Calendar.HOUR_OF_DAY, arrivaltime % 100);
        flight.setArrivalTime(cal2);

        Plane plane = new Plane();
        plane.setCapacity(capacity);
        plane.setModel(model);
        plane.setManufacturer(manufacturer);
        plane.setYear(year);

        flight.setPlane(plane);
        flightRepository.save(flight);

        return flightRepository.findById(flightnumber);
    }

    // Update flight
    @JsonView(Views.Private3.class)
    @PutMapping(path = "flight/{flightnum}")
    public @ResponseBody
    Flight updateFlight(
            @PathVariable("flightnum") String flightnumber
            , @RequestParam double price
            , @RequestParam String origin
            , @RequestParam String destination
            , @RequestParam int departuretime
            , @RequestParam int arrivaltime
            , @RequestParam String description
            , @RequestParam int capacity
            , @RequestParam String model
            , @RequestParam String manufacturer
            , @RequestParam int year) {
        Flight flight = flightRepository.findFlightByFlightNumber(flightnumber);
        if (flight == null) {
            throw new NotFoundException(
                    "Sorry, the requested flight with number " + flightnumber + " does not exist");
        }
        else {

            flight.setFlightNumber(flightnumber);
            flight.setPrice(price);
            flight.setOrigin(origin);
            flight.setDestination(destination);
            flight.setDescription(description);
            flight.setSeatsLeft(capacity);

            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.YEAR, departuretime / 1000000 % 10000);
            cal1.set(Calendar.MONTH, departuretime / 10000 % 100);
            cal1.set(Calendar.DAY_OF_MONTH, departuretime / 100 % 100);
            cal1.set(Calendar.HOUR_OF_DAY, departuretime % 100);
            flight.setDepartureTime(cal1);

            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.YEAR, arrivaltime / 1000000 % 10000);
            cal2.set(Calendar.MONTH, arrivaltime / 10000 % 100);
            cal2.set(Calendar.DAY_OF_MONTH, arrivaltime / 100 % 100);
            cal2.set(Calendar.HOUR_OF_DAY, arrivaltime % 100);
            flight.setArrivalTime(cal2);

            Plane plane = new Plane();
            plane.setCapacity(capacity);
            plane.setModel(model);
            plane.setManufacturer(manufacturer);
            plane.setYear(year);

            flight.setPlane(plane);
            flightRepository.save(flight);

            return flightRepository.findFlightByFlightNumber(flightnumber);
        }

    }


    // Delete flght
    @DeleteMapping(path = "/flight/{flightnum}")
    public @ResponseBody String deleteFlight(@PathVariable("flightnum") String flightnum) {
        Optional<Flight> flight = flightRepository.findById(flightnum);
        if (!flight.isPresent()) {
            throw new NotFoundException(flightnum);
        }
        else {
            flightRepository.deleteById(flightnum);
            return "Delete Successfully.";
        }
    }

}
