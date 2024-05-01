package AgenciaTurismo.pruebaTecnica.repository;

import AgenciaTurismo.pruebaTecnica.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCity(String city);//Metodo para obtener todos los hoteles por ciudad. Method to get all hotels by city

}
