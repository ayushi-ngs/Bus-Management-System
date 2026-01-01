import java.io.Serializable;
import java.time.LocalDate;

public class Bookings implements Serializable {

    private String bookingId;
    private int passengerId;
    private int routeId;
    private String from;
    private String to;
    private LocalDate date;
    private int seatnumbers;
    private double totalprice;
    enum Status {
        CONFIRMED, CANCELLED
    }
    private Status status;
    public Bookings(String bookingId, int passengerId, int routeId, String from, String to, LocalDate date, int seatnumbers, double totalprice, Status status) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.routeId = routeId;
        this.from = from;
        this.to = to;
        this.date = date;
        this.seatnumbers = seatnumbers;
        this.totalprice = totalprice;
        this.status = status;
    }
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public int getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }
    public int getRouteId() {
        return routeId;
    }
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getSeatnumbers() {
        return seatnumbers;
    }
    public void setSeatnumbers(int seatnumbers) {
        this.seatnumbers = seatnumbers;
    }
    public double getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

}
