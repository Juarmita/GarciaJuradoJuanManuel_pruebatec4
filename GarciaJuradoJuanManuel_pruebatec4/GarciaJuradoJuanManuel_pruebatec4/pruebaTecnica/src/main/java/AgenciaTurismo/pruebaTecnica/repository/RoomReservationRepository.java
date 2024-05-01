package AgenciaTurismo.pruebaTecnica.repository;

import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
    List<RoomReservation> findByRoom_Hotel_Id(Long hotelId);
    @Query("SELECT r FROM RoomReservation r WHERE r.room = :room AND r.checkIn = :checkIn AND r.checkOut = :checkOut")//Query para buscar una reserva identica. Query to find an identical reservation
    RoomReservation findIdenticalReservation(@Param("room") Room room, @Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut);

}
