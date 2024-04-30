package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewRoomDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import AgenciaTurismo.pruebaTecnica.repository.HotelRepository;
import AgenciaTurismo.pruebaTecnica.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    //Inyeccion de dependencias
    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private HotelRepository hotelRepo;

    //Metodo para guardar una habitacion
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

    //Metodo para obtener todas las habitaciones
    @Override//Obtener todas las habitaciones
    public List<Room> getRooms() {
        return roomRepo.findAll();
    }

    //Metodo para eliminar una habitacion por su id
    @Override
    public void deleteRoom(Long id) {
        if (roomRepo.existsById(id)) {
            roomRepo.deleteById(id);
        } else {
            throw new RuntimeException("Room with id " + id + " not found");
        }
    }

    //Metodo para obtener una habitacion por su id
    @Override
    public Room findById(Long id) {
        return roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    //Metodo para actualizar una habitacion por su id
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

    //Metodo para obtener todas las habitaciones disponibles
    public List<Room> getAvailableRooms(Date availableFrom, Date availableTo, String city) {
        // Validar las fechas
        if (availableFrom == null || availableTo == null || availableFrom.after(availableTo)) {
            throw new IllegalArgumentException("Las fechas de disponibilidad son inválidas");
        }

        // Buscar habitaciones disponibles en la ciudad para las fechas especificadas
        List<Room> availableRooms = roomRepo.findByHotelCityAndAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(city, availableTo, availableFrom);

        if (availableRooms.isEmpty()) {
            throw new RuntimeException("No hay habitaciones disponibles en la ciudad para las fechas seleccionadas");
        }

        return availableRooms;
    }



    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, String roomType, String city) {
   List<Room> rooms = roomRepo.findByRoomTypeAndHotel_City(roomType, city);

        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    private boolean isRoomAvailable(Room room, Date checkInDate, Date checkOutDate) {
        for (RoomReservation reservation : room.getRoomReservation()) {
            if (!checkInDate.after(reservation.getCheckOut()) && !checkOutDate.before(reservation.getCheckIn())) {
                return false;
            }
        }

        return true;
    }
}


