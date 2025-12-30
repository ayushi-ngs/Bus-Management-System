package exceptions;

public class SeatAlreadyBooked extends RuntimeException {
    public SeatAlreadyBooked(String message) {
        super(message);
    }
}
