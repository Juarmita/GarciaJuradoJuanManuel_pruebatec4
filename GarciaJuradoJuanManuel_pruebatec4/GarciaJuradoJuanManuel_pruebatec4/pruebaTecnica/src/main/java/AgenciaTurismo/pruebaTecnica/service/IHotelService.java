package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewHotelDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;

import java.util.List;

public interface IHotelService {
    //Creacion metodos CRUD. Creation of CRUD methods
    public void saveHotel(NewHotelDTO hotel);//Metodo para guardar un hotel. Method to save a hotel
    public List<Hotel> getHotels();//Metodo para obtener todos los hoteles. Method to get all hotels
    public void deleteHotel(Long id);//Metodo para eliminar un hotel por su id. Method to delete a hotel by its id
    public void updateHotelById(Long id, NewHotelDTO hotel);//Metodo para actualizar un hotel por su id. Method to update a hotel by its id
    public Hotel findById(Long id);//Metodo para obtener un hotel por su id. Method to get a hotel by its id


}
