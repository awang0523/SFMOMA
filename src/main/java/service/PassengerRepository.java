package service;

import model.Passenger;
import org.springframework.data.repository.CrudRepository;
import controller.*;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    Passenger findPassengerByPassengerId(String passengerId);
    Passenger deletePassengerByPassengerId(String passengerId);
}
