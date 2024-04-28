package AgenciaTurismo.pruebaTecnica.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private double pricePerNight;
    @Temporal(TemporalType.DATE)
    private Date availableFrom;
    @Temporal(TemporalType.DATE)
    private Date availableTo;

    @JsonBackReference//Se usa para evitar la recursividad en la respuesta de la API. It is used to avoid recursion in the API response.
    @ManyToOne//Relacion de muchos a uno. Many to one relationship. Una habitacion solo puede pertenecer a un hotel. A room can only belong to one hotel.
    @JoinColumn(name = "id_hotel")//Se define el nombre de la columna que se va a crear en la tabla de la base de datos. The name of the column that will be created in the database table is defined.
    private Hotel hotel;

    @OneToMany(mappedBy = "room")//Relacion de uno a muchos. One to many relationship. Una habitacion puede tener muchas reservas. A room can have many reservations.
    private List<RoomReservation> roomReservation;
}
