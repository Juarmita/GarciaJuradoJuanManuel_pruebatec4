package AgenciaTurismo.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    private String roomType;
    private String city;

    @JsonBackReference(value = "room-reservation")
    @ManyToOne
    @JoinColumn(name = "id_room")
    private Room room;

    @JsonBackReference(value = "host-reservation")
    @ManyToOne
    @JoinColumn(name = "id_host")
    private Hosts hosts;
}
