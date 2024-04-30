package AgenciaTurismo.pruebaTecnica.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private boolean itsReserved = false;//Variable para saber si esta reservado o no por defecto es false. This variable its used to know if the hotel is reserved or not, by default is false.

    @JsonManagedReference(value = "hotel-room")
    @OneToMany(mappedBy = "hotel")//Relacion de uno a muchos. One to many relationship
    private List<Room> room;

}
