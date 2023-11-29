import java.util.HashMap;
import java.util.Map;

public class FlightBookingSystem {

    private Map<String, Flight> flights;

    public FlightBookingSystem() {
        this.flights = new HashMap<>();
    }

    public void createFlight(String flightId, int totalSeats) {
        if (flightId == null || flightId.trim().isEmpty() || totalSeats <= 0) {
            throw new IllegalArgumentException("Invalid input for creating a flight");
        }

        if (flights.containsKey(flightId)) {
            throw new IllegalArgumentException("Flight with the same ID already exists");
        }

        Flight flight = new Flight(totalSeats);
        flights.put(flightId, flight);
    }

    public boolean bookSeats(String flightId, int numSeats) {
        if (!flights.containsKey(flightId) || numSeats <= 0 || numSeats > 10) {
            return false; // Booking failed
        }

        Flight flight = flights.get(flightId);
        return flight.bookSeats(numSeats);
    }

    public int getAvailableSeats(String flightId) {
        if (!flights.containsKey(flightId)) {
            throw new IllegalArgumentException("Flight not found");
        }

        Flight flight = flights.get(flightId);
        return flight.getAvailableSeats();
    }
}
