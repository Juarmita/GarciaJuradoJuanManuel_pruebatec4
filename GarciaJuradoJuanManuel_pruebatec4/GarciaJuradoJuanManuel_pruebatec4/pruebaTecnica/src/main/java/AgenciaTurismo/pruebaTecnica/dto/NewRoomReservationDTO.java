package AgenciaTurismo.pruebaTecnica.dto;

import AgenciaTurismo.pruebaTecnica.model.Hosts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewRoomReservationDTO implements Serializable {
    private int hostsQuantity;
    private Hosts hosts;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")//Formato de fecha. Date format.
    private Date checkInDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    private String roomType;
    private String city;
}
