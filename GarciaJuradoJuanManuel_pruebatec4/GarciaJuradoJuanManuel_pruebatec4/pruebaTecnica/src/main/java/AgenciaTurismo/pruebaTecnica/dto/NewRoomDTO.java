package AgenciaTurismo.pruebaTecnica.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewRoomDTO {


        private String roomType; // Tipo de habitación (individual, doble, suite, etc.)
        private double pricePerNight; // Precio por noche
        @Temporal(TemporalType.DATE)
        private Date availableFrom; // Fecha desde la que está disponible
        @Temporal(TemporalType.DATE)
        private Date availableTo; // Fecha hasta la que está disponible

        private Long hotelId; // ID del hotel al que pertenece la habitación


}
