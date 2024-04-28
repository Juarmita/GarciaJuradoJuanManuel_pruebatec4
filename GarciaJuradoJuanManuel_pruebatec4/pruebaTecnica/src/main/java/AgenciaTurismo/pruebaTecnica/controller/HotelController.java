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

    @Autowired
    private IHotelService hotelServ;

    @Autowired
    private IRoomService roomService;

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

        //Look for the hotel by id
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
    public ResponseEntity<HttpStatus> deleteHotel(@PathVariable Long id) {
        hotelServ.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //CRUD para las habitaciones de los hoteles. CRUD for hotel rooms

    @PostMapping("/hotels/rooms/new")//Crear una habitacion en un hotel. Create a room in a hotel
    public ResponseEntity<Void> createRoom(@RequestBody NewRoomDTO room) {
        roomService.saveRoom(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/hotels/rooms/delete/{id}")//Eliminar una habitacion por su id. Delete a room by its id
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/hotels/rooms/edit/{id}")//Editar una habitacion por su id. Edit a room by its id
    public ResponseEntity<Void> updateRoom(
            @PathVariable Long id,
            @RequestParam("roomType") String roomTypeModif,
            @RequestParam("pricePerNight") double pricePerNightModif,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("availableFrom") Date availableFromModif,//Se define el formato de la fecha. The date format is defined.
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("availableTo") Date availableToModif,
            @RequestParam("hotelId") Long hotelIdModif) {

        //Look for the room by id
        Room room = roomService.findById(id);
        if (room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Update the room
        room.setRoomType(roomTypeModif);
        room.setPricePerNight(pricePerNightModif);
        room.setAvailableFrom(availableFromModif);
        room.setAvailableTo(availableToModif);

        roomService.updateRoomById(id, room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hotels/rooms")
    public List<Room> getRooms() {
        return roomService.getRooms();
    }


}
