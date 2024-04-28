package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewRoomDTO;
import AgenciaTurismo.pruebaTecnica.model.Room;

import java.util.List;

public interface IRoomService {
    //Creacion metodos CRUD para la entidad Room y añadirla a la base de datos y asignarla a un hotel
    public void saveRoom(NewRoomDTO room);//Metodo para guardar una habitacion
    public void deleteRoom(Long id);//Metodo para eliminar una habitacion por su id
    public Room findById(Long id);//
    public void updateRoomById(Long id, Room room);//Metodo para actualizar una habitacion por su id
    public List<Room> getRooms();//Metodo para obtener todas las habitaciones
}
