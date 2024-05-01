package AgenciaTurismo.pruebaTecnica.controller;

import AgenciaTurismo.pruebaTecnica.dto.NewFlightDTO;
import AgenciaTurismo.pruebaTecnica.service.IFlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;//Se usa para crear objetos simulados de una clase. Used to create simulated objects of a class

public class FlightControllerTest {

    private FlightController flightController;
    private IFlightService flightService;

    @BeforeEach
    public void setUp() {
        flightService = mock(IFlightService.class);
        flightController = new FlightController(flightService);
    }

    @Test
    public void testCreateFlight() {
        // Given
        NewFlightDTO newFlightDTO = new NewFlightDTO();
        newFlightDTO.setOrigin("Origin");
        newFlightDTO.setDestination("Destination");
        newFlightDTO.setDepartureDate(new Date());
        newFlightDTO.setReturnDate(new Date());
        newFlightDTO.setSeats(100);
        newFlightDTO.setPrice(200.0);

        // When
        ResponseEntity<?> responseEntity = flightController.createFlight(newFlightDTO);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Flight Created", responseEntity.getBody());
    }
}
