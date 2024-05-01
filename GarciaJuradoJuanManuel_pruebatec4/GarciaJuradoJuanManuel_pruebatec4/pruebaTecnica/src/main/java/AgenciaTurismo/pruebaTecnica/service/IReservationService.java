package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.model.RoomReservation;
import java.util.List;

public interface IReservationService {
    void saveRoomReservation(RoomReservation roomReservation);
    void deleteRoomReservation(Long id);
    void updateRoomReservation(Long id, RoomReservation roomReservation);
    RoomReservation getRoomReservation(Long id);
    List<RoomReservation> getAllRoomReservations();
    boolean existsRoomReservation(Long id);

}