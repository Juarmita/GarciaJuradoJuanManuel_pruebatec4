package AgenciaTurismo.pruebaTecnica.repository;

import AgenciaTurismo.pruebaTecnica.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    //Metodo para obtener todos los vuelos por ciudad. Method to get all flights by city
    Optional<Flight> findById(Long flightId);

    //Metodo para obtener todos los vuelos por ciudad y fecha. Method to get all flights by city and date
    @Query("SELECT f FROM Flight f WHERE f.departureDate >= :dateFrom AND f.returnDate <= :dateTo AND f.origin = :origin AND f.destination = :destination")
    List<Flight> findFlightsByDatesAndCities(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo, @Param("origin") String origin, @Param("destination") String destination);
}
