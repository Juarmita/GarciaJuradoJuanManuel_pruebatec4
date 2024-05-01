package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewRoomDTO;
import AgenciaTurismo.pruebaTecnica.model.Room;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    //Creacion metodos CRUD para la entidad Room y añadirla a la base de datos y asignarla a un hotel
    //Create CRUD methods for the Room entity and add it to the database and assign it to a hotel
    public void saveRoom(NewRoomDTO room);//Metodo para guardar una habitacion. Method to save a room
    public void deleteRoom(Long id);//Metodo para eliminar una habitacion por su id. Method to delete a room by its id
    public Room findById(Long id);//Metodo para obtener una habitacion por su id. Method to get a room by its id
    public void updateRoomById(Long id, Room room);//Metodo para actualizar una habitacion por su id. Method to update a room by its id
    public List<Room> getRooms();//Metodo para obtener todas las habitaciones. Method to get all rooms

    List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate, String roomType, String city);

}
