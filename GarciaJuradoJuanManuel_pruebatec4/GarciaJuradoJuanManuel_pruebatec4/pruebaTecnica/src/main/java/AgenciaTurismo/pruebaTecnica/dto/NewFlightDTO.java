package AgenciaTurismo.pruebaTecnica.dto;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewFlightDTO implements Serializable {
    private String origin;
    private String destination;

    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private Integer seats;
    private double price;
    private String airline;
}
