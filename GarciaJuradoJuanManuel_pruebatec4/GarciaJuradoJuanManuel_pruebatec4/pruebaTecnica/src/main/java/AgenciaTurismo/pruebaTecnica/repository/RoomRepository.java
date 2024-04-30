package AgenciaTurismo.pruebaTecnica.repository;

import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelCityAndAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(String city, Date checkInDate, Date checkOutDate);
    List<Room> findByRoomTypeAndHotel_City(String roomType, String city);
    List<Room> findByHotelId(Long hotelId);
}

