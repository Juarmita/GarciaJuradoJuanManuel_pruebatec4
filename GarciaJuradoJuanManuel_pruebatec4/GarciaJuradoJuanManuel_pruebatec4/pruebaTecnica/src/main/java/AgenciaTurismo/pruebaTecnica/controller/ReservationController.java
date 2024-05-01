package AgenciaTurismo.pruebaTecnica.controller;

import AgenciaTurismo.pruebaTecnica.dto.NewRoomReservationDTO;
import AgenciaTurismo.pruebaTecnica.model.Hosts;
import AgenciaTurismo.pruebaTecnica.model.Room;
import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import AgenciaTurismo.pruebaTecnica.service.IHostService;
import AgenciaTurismo.pruebaTecnica.service.IReservationService;
import AgenciaTurismo.pruebaTecnica.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/agency", produces = MediaType.APPLICATION_JSON_VALUE)//Indico que el controlador solo devolvera JSON. I indicate that the controller will only return JSON.
public class ReservationController {

    @Autowired
    private IReservationService roomReservationService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IHostService hostsService;

    @PostMapping(value = "/room-booking/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBooking(@RequestBody NewRoomReservationDTO bookingDto) {
        try {
            //Verificar que la fecha de check-in sea anterior a la fecha de check-out. Verify that the check-in date is before the check-out date.
            if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())) {
                throw new RuntimeException("Check-in date must be before check-out date");
            }

            //Buscar las habitaciones disponibles en la ciudad seleccionada para las fechas seleccionadas. Search for available rooms in the selected city for the selected dates.
            List<Room> availableRooms = roomService.getAvailableRooms(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate(), bookingDto.getRoomType(), bookingDto.getCity());

            // Si no hay habitaciones disponibles, retornar un mensaje de error. If there are no available rooms, return an error message.
            if (availableRooms.isEmpty()) {
                return new ResponseEntity<>("No rooms available in the selected city for the selected dates", HttpStatus.NOT_FOUND);
            }

            // Tomar la primera habitación disponible. Take the first available room.
            Room room = availableRooms.get(0);
            Hosts host = bookingDto.getHosts(); //Uso del objeto hosts. Use the Hosts object from the bookingDto

            // Guardar el host. Save the host.
            hostsService.saveHost(host);

            // Verificar si la habitación ya está reservada para las fechas seleccionadas. Check if the room is already booked for the selected dates.
            for (RoomReservation existingReservation : room.getRoomReservation()) {
                if (existingReservation.getCheckIn().before(bookingDto.getCheckOutDate()) && existingReservation.getCheckOut().after(bookingDto.getCheckInDate())) {
                    return new ResponseEntity<>("Room is not available for the selected dates", HttpStatus.BAD_REQUEST);
                }
            }

            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoom(room);
            roomReservation.setCheckIn(bookingDto.getCheckInDate());
            roomReservation.setCheckOut(bookingDto.getCheckOutDate());
            roomReservation.setRoomType(bookingDto.getRoomType());
            roomReservation.setCity(bookingDto.getCity());
            roomReservation.setHosts(host);

            // Guardar la reserva. Save the reservation.
            roomReservationService.saveRoomReservation(roomReservation);

            //Calcular el coste total de la reserva. Calculate the total cost of the reservation.
            long diffInMilli = bookingDto.getCheckOutDate().getTime() - bookingDto.getCheckInDate().getTime();//Calculo de la diferencia en milisegundos. Calculation of the difference in milliseconds.
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMilli);//Conversion de milisegundos a dias. Conversion from milliseconds to days
            double totalCost = diffInDays * room.getPricePerNight();//Calculo del coste total. Calculation of the total cost.

            // Crear un mapa con la reserva y el coste total. Create a map with the reservation and the total cost.
            Map<String, Object> response = new HashMap<>();
            response.put("reservation", roomReservation);
            response.put("totalCost", totalCost);


            // Retornar la reserva creada con el coste. Return the created reservation with the cost.
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/room-booking/delete/{id}")
    //Eliminar una reserva por su id. Delete a reservation by its id
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        if (roomReservationService.existsRoomReservation(id)) {
            try {
                roomReservationService.deleteRoomReservation(id);
                return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to delete reservation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }
    }
}
