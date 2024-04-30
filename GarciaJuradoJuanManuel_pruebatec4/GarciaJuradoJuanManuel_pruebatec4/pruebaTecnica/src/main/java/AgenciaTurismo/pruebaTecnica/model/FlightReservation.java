package AgenciaTurismo.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FlightReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight; //Relacion de muchos a uno. Many to one relationship. Una reserva de vuelo solo puede tener un vuelo. A flight reservation can only have one flight.

    @JsonBackReference(value = "flight-reservation")
    @ManyToOne
    private Hosts hosts;//Relacion de muchos a uno. Many to one relationship. Una reserva de vuelo solo puede tener un anfitrion. A flight reservation can only have one host.
}
