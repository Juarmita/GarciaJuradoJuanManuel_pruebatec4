package AgenciaTurismo.pruebaTecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    @OneToMany(mappedBy = "hosts")//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de habitacion. A host can have many room reservations.
    private List<RoomReservation> roomReservation;//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.

    @OneToMany(mappedBy = "hosts")//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.
    private List<FlightReservation> flightReservation;//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.
}
