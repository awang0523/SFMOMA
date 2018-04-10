package service;

import model.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, String> {
    Flight findFlightByFlightNumber(String flightNumber);
}
