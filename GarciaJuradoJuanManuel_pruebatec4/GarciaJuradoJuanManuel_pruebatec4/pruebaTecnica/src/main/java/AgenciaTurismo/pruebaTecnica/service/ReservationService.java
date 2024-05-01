package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import AgenciaTurismo.pruebaTecnica.repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Override
    public void saveRoomReservation(RoomReservation roomReservation) {
        // Buscar una reserva existente con las mismas características. Find an existing reservation with the same characteristics
        RoomReservation existingReservation = roomReservationRepository.findIdenticalReservation(roomReservation.getRoom(), roomReservation.getCheckIn(), roomReservation.getCheckOut());

        // Si la reserva existente no es nula, entonces ya existe una reserva idéntica. If the existing reservation is not null, then an identical reservation already exists
        if (existingReservation != null) {
            throw new RuntimeException("An identical reservation already exists");
        }

        // Si no se encontró una reserva idéntica, guardar la nueva reserva. If an identical reservation was not found, save the new reservation
        roomReservationRepository.save(roomReservation);
    }

    @Override
    //Metodo para eliminar una reserva de habitacion por su id. Method to delete a room reservation by its id
    public void deleteRoomReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    //Metodo para actualizar una reserva de habitacion por su id. Method to update a room reservation by its id
    public void updateRoomReservation(Long id, RoomReservation roomReservation) {
        if(roomReservationRepository.existsById(id)) {
            roomReservation.setId(id);
            roomReservationRepository.save(roomReservation);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    @Override
    //Metodo para obtener una reserva de habitacion por su id. Method to get a room reservation by its id
    public RoomReservation getRoomReservation(Long id) {
        return roomReservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    //Metodo para verificar si existe una reserva de habitacion por su id. Method to check if a room reservation exists by its id
    public boolean existsRoomReservation(Long id) {
        return roomReservationRepository.existsById(id);
    }

    @Override
    //Metodo para obtener todas las reservas de habitacion. Method to get all room reservations
    public List<RoomReservation> getAllRoomReservations() {
        return roomReservationRepository.findAll();
    }
}