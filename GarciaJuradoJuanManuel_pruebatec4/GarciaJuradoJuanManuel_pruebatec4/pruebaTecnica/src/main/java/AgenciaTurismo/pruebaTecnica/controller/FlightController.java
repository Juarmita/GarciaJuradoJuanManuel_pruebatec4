package AgenciaTurismo.pruebaTecnica.controller;

import AgenciaTurismo.pruebaTecnica.dto.FlightBookingDTO;
import AgenciaTurismo.pruebaTecnica.dto.NewFlightDTO;
import AgenciaTurismo.pruebaTecnica.model.Flight;
import AgenciaTurismo.pruebaTecnica.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    //-----------------------------------USADO EN TEST-----------------------------------
    @Autowired
    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    public FlightController() {
    }

    //-----------------------------------------------------------------------------------

    //-----------------------------------Flights-----------------------------------

    //CRUD para los vuelos. CRUD for flights
    //Crear un vuelo. Create a flight
    @PostMapping("/flights/new")
    public ResponseEntity<?> createFlight(@RequestBody NewFlightDTO flight) {
        flightService.saveFlight(flight);
        return new ResponseEntity<>("Flight Created", HttpStatus.CREATED);
    }

    //Obtener todos los vuelos. Get all flights
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }

    //Obtener un vuelo por su id. Get a flight by its id
    @GetMapping("/flights/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    //Eliminar un vuelo por su id. Delete a flight by its id
    @DeleteMapping("/flights/delete/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>("Flight Deleted", HttpStatus.OK);
    }

    //Editar un vuelo por su id. Edit a flight by its id
    @PutMapping("/flights/edit/{id}")
    public ResponseEntity<?> updateFlight(
            @PathVariable Long id,
            @RequestParam("departureCity") String originModif,
            @RequestParam("arrivalCity") String destinationModif,
            @RequestParam("price") double priceModif,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("departureDate") Date departureDateModif,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("returnDate") Date returnDateModif,
            @RequestParam("seats") int seatsModif,
            @RequestParam("airline") String airlineModif){

        NewFlightDTO newFlight = new NewFlightDTO();
        newFlight.setOrigin(originModif);
        newFlight.setDestination(destinationModif);
        newFlight.setPrice(priceModif);
        newFlight.setDepartureDate(departureDateModif);
        newFlight.setReturnDate(returnDateModif);
        newFlight.setSeats(seatsModif);
        newFlight.setAirline(airlineModif);

        flightService.updateFlightById(id, newFlight);//Se actualiza el vuelo. The flight is updated
        return new ResponseEntity<>("Flight Updated", HttpStatus.OK);
    }

    //Obtener vuelos por fechas y ciudades. Get flights by dates and cities
    @GetMapping("/flights/byDatesAndCities")
    public List<Flight> getFlights(
            @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo,
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination) {
        return flightService.findFlightsByDatesAndCities(dateFrom, dateTo, origin, destination);
    }

    //Reservar un vuelo. Book a flight
    @PostMapping("/flight-booking/new")
    public ResponseEntity<?> createFlightBooking(@RequestBody FlightBookingDTO booking) {
            double totalCost = flightService.bookFlight(booking);
            return new ResponseEntity<>("Flight booked. Total cost: " + totalCost, HttpStatus.CREATED);
        }

    //Listar reservas de vuelos. List flight bookings
    @GetMapping("/flight-booking/list")
    public List<FlightBookingDTO> getFlightBookings() {
        return flightService.getFlightBookings();
    }
}


