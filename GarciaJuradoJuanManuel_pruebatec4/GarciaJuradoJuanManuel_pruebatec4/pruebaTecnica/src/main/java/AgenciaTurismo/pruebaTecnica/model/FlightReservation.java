package AgenciaTurismo.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    private Integer seats;
    private String classType;

    @JsonBackReference(value = "flight-reservation")
    @ManyToOne
    @JoinColumn(name = "id_flight")
    private Flight flight; //Relacion de muchos a uno. Many to one relationship. Una reserva de vuelo solo puede tener un vuelo. A flight reservation can only have one flight.


    @JsonBackReference(value = "hostFlight-reservation")
    @ManyToOne
    @JoinColumn(name = "id_host")
    private Hosts hosts;//Relacion de muchos a uno. Many to one relationship. Una reserva de vuelo solo puede tener un anfitrion. A flight reservation can only have one host.
}
