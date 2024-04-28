package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewHotelDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;

import java.util.List;

public interface IHotelService {
    //Creacion metodos CRUD
    public void saveHotel(NewHotelDTO hotel);//Metodo para guardar un hotel
    public List<Hotel> getHotels();//Metodo para obtener todos los hoteles
    public void deleteHotel(Long id);//Metodo para eliminar un hotel por su id
    public void updateHotelById(Long id, NewHotelDTO hotel);//Metodo para actualizar un hotel por su id
    public Hotel findById(Long id);//


}
