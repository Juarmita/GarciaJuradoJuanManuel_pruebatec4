package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.NewHotelDTO;
import AgenciaTurismo.pruebaTecnica.model.Hotel;
import AgenciaTurismo.pruebaTecnica.repository.HotelRepository;
import AgenciaTurismo.pruebaTecnica.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService  {



    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private RoomRepository roomRepo;

    @Override
    public void saveHotel(NewHotelDTO newHotel) {
        Hotel hotel = new Hotel();
        hotel.setName(newHotel.getName());
        hotel.setCity(newHotel.getCity());
        hotel.setItsReserved(newHotel.isItsReserved());

        hotelRepo.save(hotel);

    }

    @Override
    public List<Hotel> getHotels() {

        return hotelRepo.findAll();
    }

    @Override
    public void deleteHotel(Long id) {

        hotelRepo.deleteById(id);
    }

    @Override
    public Hotel findById(Long id) {

        return hotelRepo.findById(id).orElse(null);
    }

    @Override
    public void updateHotelById(Long id, NewHotelDTO hotel) {
        Hotel hotelToUpdate = hotelRepo.findById(id).get();
        hotelToUpdate.setName(hotel.getName());
        hotelToUpdate.setCity(hotel.getCity());
        hotelToUpdate.setItsReserved(hotel.isItsReserved());
        hotelRepo.save(hotelToUpdate);

    }


}
