package AgenciaTurismo.pruebaTecnica.controller;

import AgenciaTurismo.pruebaTecnica.dto.NewHotelDTO;
import AgenciaTurismo.pruebaTecnica.dto.NewRoomDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.service.IHotelService;
import AgenciaTurismo.pruebaTecnica.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class HotelController {

    //Inyeccion de dependencias
    @Autowired
    private IHotelService hotelServ;

    @Autowired
    private IRoomService roomService;


    //-----------------------------------Hotels-----------------------------------
    //CRUD para los hoteles. CRUD for hotels
    @GetMapping("/hotels")
    public List<Hotel> getHotels() {//Solo se puede acceder estando autorizado. Only authorized access

        return hotelServ.getHotels();
    }

    @GetMapping("/hotels/{id}")//Solo se puede acceder estando autorizado. Only authorized access
    public Hotel findById(@PathVariable Long id) {
        return hotelServ.findById(id);
    }

    @PostMapping("/hotels/new")//Crear un hotel. Create a hotel
    public String createHotel(@RequestBody NewHotelDTO hotel) {//Uso de DTO para la creacion de un hotel. Use of DTO to create a hotel
        hotelServ.saveHotel(hotel);
        System.out.println(hotel.toString());
        return "Hotel created successfully";
    }

    @PutMapping("/hotels/edit/{id}")//Editar un hotel por su id. Edit a hotel by its id
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestParam("name") String nameModif, @RequestParam("city") String cityModif, @RequestParam("itsReserved") boolean itsReservedModif) {

        //Buscar el hotel por su id. Look for the hotel by id
        Hotel hotel = hotelServ.findById(id);
        if (hotel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hotel.setName(nameModif);
        hotel.setCity(cityModif);
        hotel.setItsReserved(itsReservedModif);



        NewHotelDTO hotelDTO = convertEntityToDTO(hotel);
        hotelServ.saveHotel(hotelDTO);
        return new ResponseEntity<>(hotel, HttpStatus.OK);


    }

    private NewHotelDTO convertEntityToDTO(Hotel hotel) {
        NewHotelDTO hotelDto = new NewHotelDTO();
        hotelDto.setName(hotel.getName());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setItsReserved(hotel.isItsReserved());
        return hotelDto;
    }

    @DeleteMapping("/hotels/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) {

        //Buscar el hotel por su id. Look for the hotel by id
        Hotel hotel = hotelServ.findById(id);
        if (hotel == null) {
            return new ResponseEntity<>("Hotel not found", HttpStatus.NOT_FOUND);
        }
        hotelServ.deleteHotel(id);
        return new ResponseEntity<>("Hotel deleted",HttpStatus.OK);
    }

    //-----------------------------------Rooms-----------------------------------
    //CRUD para las habitaciones de los hoteles. CRUD for hotel rooms

    //Crear una habitacion en un hotel. Create a room in a hotel
    @PostMapping("/hotels/rooms/new")//Crear una habitacion en un hotel. Create a room in a hotel
    public ResponseEntity<?> createRoom(@RequestBody NewRoomDTO room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>("Room created succesfully",HttpStatus.CREATED);
    }

    //Eliminar una habitacion por su id. Delete a room by its id
    @DeleteMapping("/hotels/rooms/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        Room room = roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }

        roomService.deleteRoom(id);
        return new ResponseEntity<>("Room deleted", HttpStatus.OK);
    }

    //Obtener una habitacion por su id. Get a room by its id
    @PutMapping("/hotels/rooms/edit/{id}")//Editar una habitacion por su id. Edit a room by its id
    public ResponseEntity<?> updateRoom(
            @PathVariable Long id,
            @RequestParam("roomType") String roomTypeModif,
            @RequestParam("pricePerNight") double pricePerNightModif,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("availableFrom") Date availableFromModif,//Se define el formato de la fecha. The date format is defined.
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("availableTo") Date availableToModif,
            @RequestParam("hotelId") Long hotelIdModif) {

        //Buscar la habitacion por su id. Look for the room by id
        Room room = roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }

        //Actualizar la habitacion. Update the room
        room.setRoomType(roomTypeModif);
        room.setPricePerNight(pricePerNightModif);
        room.setAvailableFrom(availableFromModif);
        room.setAvailableTo(availableToModif);

        roomService.updateRoomById(id, room);
        return new ResponseEntity<>("Room Updated", HttpStatus.OK);
    }

    //Obtener todas las habitaciones. Get all rooms
    @GetMapping("/hotels/rooms")//Necesita estar autorizado. Needs to be authorized
    public List<Room> getRooms() {

        return roomService.getRooms();
    }

    //Obtener listado de habitaciones por un rango de fechas y ciudad. Get a list of rooms by date range and city
    @GetMapping("/rooms")
    public List<Room> getAvailableRooms(
            @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("dateFrom") Date availableFrom,
            @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("dateTo") Date availableTo,
            @RequestParam("roomType") String roomType,
            @RequestParam("destination") String city) {
        return roomService.getAvailableRooms(availableFrom, availableTo, roomType, city);
    }
}
