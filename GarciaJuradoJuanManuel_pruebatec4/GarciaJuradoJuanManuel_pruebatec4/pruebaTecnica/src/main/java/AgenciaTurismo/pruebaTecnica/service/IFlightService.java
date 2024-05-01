package AgenciaTurismo.pruebaTecnica.service;

import AgenciaTurismo.pruebaTecnica.dto.FlightBookingDTO;
import AgenciaTurismo.pruebaTecnica.dto.NewFlightDTO;
import AgenciaTurismo.pruebaTecnica.model.Flight;

import java.util.Date;
import java.util.List;

public interface IFlightService {
    //Creacion de metodos para la clase FlightService. Creation of methods for the FlightService class
    public void saveFlight(NewFlightDTO flight);//Metodo para guardar un vuelo. Method to save a flight
    public List<Flight> getFlights();//Metodo para obtener todos los vuelos. Method to get all flights
    public void deleteFlight(Long id);//Metodo para eliminar un vuelo por su id. Method to delete a flight by its id
    public void updateFlightById(Long id, NewFlightDTO flight);//Metodo para actualizar un vuelo por su id. Method to update a flight by its id
    public Flight findById(Long id);//

    //Metodo para obtener todos los vuelos por ciudad y fecha. Method to get all flights by city and date
    public List<Flight> findFlightsByDatesAndCities(Date dateFrom, Date dateTo, String origin, String destination);

    //Metodo para reservar un vuelo. Method to book a flight
    public double bookFlight(FlightBookingDTO flightBookingDTO);

    //Metodo para obtener todas las reservas de vuelos. Method to get all flight bookings
    List<FlightBookingDTO> getFlightBookings();
}
