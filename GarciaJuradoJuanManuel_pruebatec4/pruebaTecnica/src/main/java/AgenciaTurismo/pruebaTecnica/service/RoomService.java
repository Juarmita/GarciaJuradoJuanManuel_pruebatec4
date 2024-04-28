package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewRoomDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.repository.HotelRepository;
import AgenciaTurismo.pruebaTecnica.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private HotelRepository hotelRepo;


    @Override
    public void saveRoom(NewRoomDTO roomDto) {
        // Convertir el DTO a la entidad Room
        Room room = new Room();
        room.setRoomType(roomDto.getRoomType());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setAvailableFrom(roomDto.getAvailableFrom());
        room.setAvailableTo(roomDto.getAvailableTo());

        // Buscar el hotel al que pertenece la habitación
        Hotel hotel = hotelRepo.findById(roomDto.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        // Asignar el hotel a la habitación
        room.setHotel(hotel);

        // Guardar la habitación en la base de datos
        roomRepo.save(room);
    }

    @Override//Obtener todas las habitaciones
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    @Override
    public void deleteRoom(Long id) {
        if (roomRepo.existsById(id)) {
            roomRepo.deleteById(id);
        } else {
            throw new RuntimeException("Room with id " + id + " not found");
        }
    }

    @Override
    public Room findById(Long id) {
        return roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public void updateRoomById(Long id, Room room) {
        // Lógica para actualizar la habitación en la base de datos
        Room existingRoom = roomRepo.findById(id).orElse(null);
        if (existingRoom != null) {
            existingRoom.setRoomType(room.getRoomType());
            existingRoom.setPricePerNight(room.getPricePerNight());
            existingRoom.setAvailableFrom(room.getAvailableFrom());
            existingRoom.setAvailableTo(room.getAvailableTo());
            // Otras actualizaciones necesarias
            roomRepo.save(existingRoom);
        }
    }



}

