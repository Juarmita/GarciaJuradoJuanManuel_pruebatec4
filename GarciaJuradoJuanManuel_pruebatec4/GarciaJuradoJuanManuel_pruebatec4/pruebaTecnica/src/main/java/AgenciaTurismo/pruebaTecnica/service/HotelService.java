package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewHotelDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import AgenciaTurismo.pruebaTecnica.repository.HotelRepository;
import AgenciaTurismo.pruebaTecnica.repository.RoomRepository;
import AgenciaTurismo.pruebaTecnica.repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService  {



    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RoomReservationRepository roomReservationRepo;

    @Override
    public void saveHotel(NewHotelDTO newHotel) {
        Hotel hotel = new Hotel();
        hotel.setName(newHotel.getName());
        hotel.setCity(newHotel.getCity());
        hotel.setItsReserved(newHotel.isItsReserved());

        hotelRepo.save(hotel);

    }

    @Override
    public List<Hotel> getHotels() {

        return hotelRepo.findAll();
    }

    @Override
    public void deleteHotel(Long id) {
        // Buscar el hotel por su ID. Find the hotel by its ID
        Hotel hotelToDelete = hotelRepo.findById(id).orElse(null);

        if (hotelToDelete != null) {
            // Verificar si hay reservas de habitaciones asociadas con el hotel. Check if there are room reservations associated with the hotel
            List<RoomReservation> reservations = roomReservationRepo.findByRoom_Hotel_Id(id);
            if (!reservations.isEmpty()) {
                throw new RuntimeException("Cannot delete hotel with associated room reservations");
            }

            // Actualizar las relaciones de las habitaciones asociadas a este hotel. Update the relationships of the rooms associated with this hotel
            List<Room> rooms = roomRepo.findByHotelId(id);
            for (Room room : rooms) {
                room.setHotel(null);
                roomRepo.save(room);
            }

            // Luego de actualizar las relaciones, eliminar el hotel. After updating the relationships, delete the hotel
            hotelRepo.deleteById(id);
        }
    }

    @Override
    public Hotel findById(Long id) {

        return hotelRepo.findById(id).orElse(null);
    }

    @Override
    public void updateHotelById(Long id, NewHotelDTO hotel) {
        Hotel hotelToUpdate = hotelRepo.findById(id).get();
        hotelToUpdate.setName(hotel.getName());
        hotelToUpdate.setCity(hotel.getCity());
        hotelToUpdate.setItsReserved(hotel.isItsReserved());
        hotelRepo.save(hotelToUpdate);

    }


}
