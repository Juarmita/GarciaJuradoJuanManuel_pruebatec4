package AgenciaTurismo.pruebaTecnica.dto;

import AgenciaTurismo.pruebaTecnica.model.Hosts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightBookingDTO {
    private int numberOfPeople;
    private String origin;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;



}
