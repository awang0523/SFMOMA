package service;

import model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Reservation deleteReservationByPassengerPassengerId(String passengerId);
    Reservation getReservationsByPassengerPassengerId(String passengerId);
    Reservation findReservationByReservationNumber(String reservationnum);
}
