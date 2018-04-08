package model;

import org.springframework.data.repository.CrudRepository;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    Passenger findPassengerByPassengerId(String passengerId);
}
