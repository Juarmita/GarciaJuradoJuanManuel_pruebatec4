package AgenciaTurismo.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIdentityInfo(//Se define la identidad de la clase. The identity of the class is defined.
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

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

    @JsonManagedReference(value = "host-reservation")
    @OneToMany(mappedBy = "hosts")//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de habitacion. A host can have many room reservations.
    private List<RoomReservation> roomReservation;//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.

    @JsonManagedReference(value = "flight-reservation")
    @OneToMany(mappedBy = "hosts")//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.
    private List<FlightReservation> flightReservation;//Relacion de uno a muchos. One to many relationship. Un anfitrion puede tener muchas reservas de vuelo. A host can have many flight reservations.
}
