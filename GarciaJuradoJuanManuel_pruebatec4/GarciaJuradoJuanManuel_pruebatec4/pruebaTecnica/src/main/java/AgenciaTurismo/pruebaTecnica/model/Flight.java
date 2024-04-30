package AgenciaTurismo.pruebaTecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String destination;
    private String classType;
    private double price;
    @Temporal(TemporalType.DATE)//Se define el tipo de dato de la fecha. The type of the date is defined
    private Date departureDate;
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @OneToMany(mappedBy = "flight")//Relacion de uno a muchos. One to many relationship. Un vuelo puede tener muchas reservas. A flight can have many reservations.
    private List<FlightReservation> flightReservation;


}
