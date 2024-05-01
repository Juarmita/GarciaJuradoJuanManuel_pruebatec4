package AgenciaTurismo.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private double price;
    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column(name = "return_date")//Se define el nombre de la columna. The name of the column is defined.
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    private Integer seats;
    private String airline;

    @JsonManagedReference(value = "flight-reservation")//Se define el nombre de la referencia. The name of the reference is defined.
    @OneToMany(mappedBy = "flight")//Relacion de uno a muchos. One to many relationship. Un vuelo puede tener muchas reservas. A flight can have many reservations.
    private List<FlightReservation> flightReservation;


}
