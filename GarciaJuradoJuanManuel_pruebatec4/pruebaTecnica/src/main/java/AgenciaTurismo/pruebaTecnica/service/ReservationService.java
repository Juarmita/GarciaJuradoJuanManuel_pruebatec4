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
        roomReservationRepository.save(roomReservation);
    }

    @Override
    public void deleteRoomReservation(Long id) {
        roomReservationRepository.deleteById(id);
    }

    @Override
    public void updateRoomReservation(Long id, RoomReservation roomReservation) {
        if(roomReservationRepository.existsById(id)) {
            roomReservation.setId(id);
            roomReservationRepository.save(roomReservation);
        } else {
            throw new RuntimeException("Reservation not found");
        }
    }

    @Override
    public RoomReservation getRoomReservation(Long id) {
        return roomReservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public List<RoomReservation> getAllRoomReservations() {
        return roomReservationRepository.findAll();
    }
}