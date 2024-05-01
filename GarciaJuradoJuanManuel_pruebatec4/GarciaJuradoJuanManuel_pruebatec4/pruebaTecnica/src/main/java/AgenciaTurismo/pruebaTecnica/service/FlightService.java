package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.FlightBookingDTO;
import AgenciaTurismo.pruebaTecnica.dto.NewFlightDTO;
import AgenciaTurismo.pruebaTecnica.model.Flight;
import AgenciaTurismo.pruebaTecnica.model.FlightReservation;
import AgenciaTurismo.pruebaTecnica.repository.FlightRepository;
import AgenciaTurismo.pruebaTecnica.repository.FlightReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements IFlightService{

    @Autowired
    private FlightReservationRepository flightReservationRepository;

    @Autowired
    private FlightRepository flightRepo;

        //Metodo para guardar un vuelo. Method to save a flight
    public void saveFlight(NewFlightDTO newFlight) {
        Flight flight = new Flight();
        flight.setOrigin(newFlight.getOrigin());
        flight.setDestination(newFlight.getDestination());
        flight.setDepartureDate(newFlight.getDepartureDate());
        flight.setReturnDate(newFlight.getReturnDate());
        flight.setPrice(newFlight.getPrice());
        flight.setSeats(newFlight.getSeats());
        flight.setAirline(newFlight.getAirline());

        flightRepo.save(flight);
    }

    //Metodo para obtener todos los vuelos. Method to get all flights
    public List<Flight> getFlights() {
        return flightRepo.findAll();
    }

    //Metodo para obtener un vuelo por su id. Method to get a flight by its id
    public Flight findById(Long id) {
        return flightRepo.findById(id).orElse(null);
    }

    //Metodo para eliminar un vuelo por su id. Method to delete a flight by its id
    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }

    //Metodo para actualizar un vuelo por su id. Method to update a flight by its id
    public void updateFlightById(Long id, NewFlightDTO newFlight) {
        Flight flight = flightRepo.findById(id).orElse(null);//Buscar el vuelo por su ID. Find the flight by its ID
        if (flight != null) {//Si el vuelo existe. If the flight exists
            flight.setOrigin(newFlight.getOrigin());
            flight.setDestination(newFlight.getDestination());
            flight.setDepartureDate(newFlight.getDepartureDate());
            flight.setReturnDate(newFlight.getReturnDate());
            flight.setPrice(newFlight.getPrice());
            flight.setSeats(newFlight.getSeats());
            flight.setAirline(newFlight.getAirline());

            flightRepo.save(flight);
        }
    }

    //Metodo para buscar vuelos por fechas y ciudades. Method to search flights by dates and cities
    public List<Flight> findFlightsByDatesAndCities(Date dateFrom, Date dateTo, String origin, String destination) {
        return flightRepo.findFlightsByDatesAndCities(dateFrom, dateTo, origin, destination);
    }

    //Metodo para reservar un vuelo. Method to book a flight
    @Override
    public double bookFlight(FlightBookingDTO booking) {
        //Encuentra los vuelos por fechas y ciudades. Find flights by dates and cities
        List<Flight> flights = flightRepo.findFlightsByDatesAndCities(
                booking.getDepartureDate(),
                booking.getReturnDate(),
                booking.getOrigin(),
                booking.getDestination()
        );

        if (flights.isEmpty()) {
            throw new RuntimeException("No flights found that match the booking criteria.");
        }

        //Supuesto: Se reserva el primer vuelo que se encuentra. Assumption: The first flight found is booked.
        Flight flight = flights.get(0);

        //Calcula el costo total. Calculate the total cost
        double totalCost = flight.getPrice() * booking.getNumberOfPeople();

        //Se crea la reserva de vuelo. Create the flight reservation
        FlightReservation reservation = new FlightReservation();
        reservation.setFlight(flight);
        reservation.setSeats(booking.getNumberOfPeople());
        reservation.setDepartureDate(booking.getDepartureDate());
        reservation.setReturnDate(booking.getReturnDate());

        //Guarda la reserva de vuelo. Save the flight reservation
        flightReservationRepository.save(reservation);

        //Devuelve el costo total. Return the total cost
        return totalCost;
    }


    //Metodo para obtener todas las reservas de vuelos. Method to get all flight bookings
    public List<FlightBookingDTO> getFlightBookings() {
        List<FlightReservation> reservations = flightReservationRepository.findAll();
        if (reservations.isEmpty()) {
            System.out.println("No flight reservations found.");
            return new ArrayList<>();
        }
        //Mapea las reservas a DTO. Map reservations to DTO
        List<FlightBookingDTO> bookingDTOs = reservations.stream()
                .filter(reservation -> reservation.getSeats() > 0) // Filter out reservations with no seats booked
                .map(reservation -> {
                    FlightBookingDTO dto = new FlightBookingDTO();
                    dto.setNumberOfPeople(reservation.getSeats());
                    dto.setOrigin(reservation.getFlight().getOrigin());
                    dto.setDestination(reservation.getFlight().getDestination());
                    dto.setDepartureDate(reservation.getDepartureDate());
                    dto.setReturnDate(reservation.getReturnDate());
                    return dto;
                })
                .collect(Collectors.toList());

        return bookingDTOs;
    }
    }

