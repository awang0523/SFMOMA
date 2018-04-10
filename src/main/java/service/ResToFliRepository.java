package service;

import model.ResToFli;
import org.springframework.data.repository.CrudRepository;

public interface ResToFliRepository extends CrudRepository<ResToFli, String> {
    ResToFli deleteResToFliByReservationNumber(String reservationnum);
    ResToFli deleteResToFliByFlightNumber(String flightnum);
}
