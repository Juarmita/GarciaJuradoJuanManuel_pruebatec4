package AgenciaTurismo.pruebaTecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomReservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date checkIn;
    @Temporal(TemporalType.DATE)
    private Date checkOut;

    @ManyToOne
    @JoinColumn(name = "id_room")//Se define el nombre de la columna que se va a relacionar. The name of the column that is going to be related is defined
    private Room room;//Relacion de muchos a uno. Many to one relationship. Una reserva de habitacion solo puede tener una habitacion. A room reservation can only have one room.

    @ManyToOne
    @JoinColumn(name = "id_host")//Se define el nombre de la columna que se va a relacionar. The name of the column that is going to be related is defined
    private Hosts hosts;//Relacion de muchos a uno. Many to one relationship. Una reserva de habitacion solo puede tener un anfitrion. A room reservation can only have one host.

}
