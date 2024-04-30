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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class ReservationController {

    @Autowired
    private IReservationService roomReservationService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IHostService hostsService;

    @PostMapping("/room-booking/new")
    public ResponseEntity<?> createBooking(@RequestBody NewRoomReservationDTO bookingDto) {
        try {
            // Validate the dates
            if (bookingDto.getCheckInDate().after(bookingDto.getCheckOutDate())) {
                throw new RuntimeException("Check-in date must be before check-out date");
            }

            // Search for available rooms that match the room type and city
            List<Room> availableRooms = roomService.getAvailableRooms(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate(), bookingDto.getRoomType(), bookingDto.getCity());

            // If there are no available rooms, return an error message
            if (availableRooms.isEmpty()) {
                return new ResponseEntity<>("No rooms available in the selected city for the selected dates", HttpStatus.NOT_FOUND);
            }

            // Create the reservation with the first available room
            Room room = availableRooms.get(0);
            Hosts host = bookingDto.getHosts(); // Use the Hosts object from the bookingDto

            // Save the host
            hostsService.saveHost(host);

            // Verify the room's availability
            for (RoomReservation existingReservation : room.getRoomReservation()) {
                if (existingReservation.getCheckIn().before(bookingDto.getCheckOutDate()) && existingReservation.getCheckOut().after(bookingDto.getCheckInDate())) {
                    return new ResponseEntity<>("Room is not available for the selected dates", HttpStatus.BAD_REQUEST);
                }
            }

            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoom(room);
            roomReservation.setCheckIn(bookingDto.getCheckInDate());
            roomReservation.setCheckOut(bookingDto.getCheckOutDate());
            roomReservation.setHosts(host);

            // Save the reservation
            roomReservationService.saveRoomReservation(roomReservation);

            // Return the created reservation
            return new ResponseEntity<>(roomReservation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
