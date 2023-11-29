public class Flight {

    private int totalSeats;
    private int bookedSeats;

    public Flight(int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Invalid input for creating a flight");
        }
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats <= 0 || bookedSeats + numSeats > totalSeats) {
            return false; // Booking failed
        }
        bookedSeats += numSeats;
        return true; // Booking successful
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }
}
