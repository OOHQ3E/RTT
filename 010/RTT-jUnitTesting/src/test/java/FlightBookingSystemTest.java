import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class FlightBookingSystemTest {

    @Test
    void testCreateFlight() {
        FlightBookingSystem bookingSystem = new FlightBookingSystem();
        bookingSystem.createFlight("F001", 100);

        int expected = 100;
        int actual = bookingSystem.getAvailableSeats("F001");

        assertEquals(expected,actual);
    }


    @Test
    void testGetAvailableSeats() {
        FlightBookingSystem bookingSystem = new FlightBookingSystem();
        bookingSystem.createFlight("F002", 100);
        bookingSystem.bookSeats("F002", 2);

        int expected = 98;
        int actual = bookingSystem.getAvailableSeats("F002");

        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("paramsForBoundaryAndEquivalentPartitioning")
    void testBoundaryAndEquivalentPartitioning(int numSeats, boolean expectedResult) {
        FlightBookingSystem bookingSystem = new FlightBookingSystem();
        String flightId = "BoundaryPartitioningFlight";
        int totalSeats = 100;

        bookingSystem.createFlight(flightId, totalSeats);
        boolean actualResult = bookingSystem.bookSeats(flightId, numSeats);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> paramsForBoundaryAndEquivalentPartitioning() {
        return Stream.of(
                Arguments.of(0, false),   // Below valid range
                Arguments.of(1, true),    // On the boundary of the valid range
                Arguments.of(10, true),   // On the boundary of the valid range
                Arguments.of(11, false)   // Above valid range
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForInvalidOperations")
    void testInvalidOperations(String flightId, int totalSeats, int bookedSeats, String operation, boolean expected) {
        String output = """
                Parameterized Test For Invalid Operations :
                    FlightID: %s
                    Total Seats: %d
                    Booked Seats: %d
                    Operation: %s
                    Expected result: %b
                """.formatted(flightId, totalSeats, bookedSeats, operation, expected);
        System.out.println(output);

        FlightBookingSystem bookingSystem = new FlightBookingSystem();
        // Parameterized Testing for invalid operations
        switch (operation) {
            case "createFlight":
                assertThrows(IllegalArgumentException.class, () -> bookingSystem.createFlight(flightId, totalSeats));
                break;
            case "bookSeats":
                // Ensure the flight exists for bookSeats test
                bookingSystem.createFlight("InvalidFlight", 10);
                boolean actual = bookingSystem.bookSeats(flightId, bookedSeats);

                assertEquals(expected, actual);
                break;
            case "getAvailableSeats":
                assertThrows(IllegalArgumentException.class, () -> bookingSystem.getAvailableSeats(flightId));
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type");
        }
    }

    private static Stream<Arguments> paramsForInvalidOperations() {
        return Stream.of(
                // Parameters:
                // String flightId, int totalSeats, int numOfBookedSeats, String operation, boolean expected
                // name is null
                Arguments.of(null, 5, 2, "createFlight", false),

                // creating flight with negative number of seats
                Arguments.of("F003", -1, 2, "createFlight", false),

                // booking flight with 0 seats
                Arguments.of("F004", 10, 0, "bookSeats", false),

                // booking flight with nonExistent ID
                Arguments.of("NonexistentFlight", 10, 5, "bookSeats", false),

                // booking flight with more than 10 seats
                Arguments.of("F005", 15, 12, "bookSeats", false),

                // getting available seats of a nonExistent flight
                Arguments.of("NonexistentFlight", 8, 2, "getAvailableSeats", false),

                // trying to book more than the available seats
                Arguments.of("F006", 5, 6, "bookSeats", false)
        );
    }
    @RepeatedTest(2) // RepeatedTest annotation with the default repeat count of 2
    void testRepeatedCreateFlight(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo);
        FlightBookingSystem bookingSystem = new FlightBookingSystem();

        // Repeated Testing for creating the same flight multiple times
        bookingSystem.createFlight("RepeatedFlight", 8);
        assertThrows(IllegalArgumentException.class, () -> bookingSystem.createFlight("RepeatedFlight", 8));
    }

}
