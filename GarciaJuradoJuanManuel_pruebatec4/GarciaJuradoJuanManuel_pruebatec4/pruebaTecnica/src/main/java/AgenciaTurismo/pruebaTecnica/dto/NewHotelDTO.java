package AgenciaTurismo.pruebaTecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewHotelDTO implements Serializable{
    private Long id_hotel;
    private String name;
    private String city;
    private boolean itsReserved = false;
}
