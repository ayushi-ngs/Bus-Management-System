import java.time.LocalDate;
import java.time.LocalTime;

public class BusRoute {
    private int routeId;
    private String from;
    private String to;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private LocalDate date;
    private int totalSeats;
    private int availableSeats;
    private double price;


    public BusRoute(int routeId,String from,String to,LocalTime arrivalTime,LocalTime departureTime,LocalDate date,int totalSeats,double price) {
        this.routeId = routeId;
        this.from = from;
        this.to = to;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.date = date;
        this.totalSeats = totalSeats;
        this.price = price;
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
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return " ";
    }
}
