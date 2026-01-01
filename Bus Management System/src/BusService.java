import exceptions.BookingNotFound;
import exceptions.RouteNotFound;
import exceptions.SeatNotAvailable;

import java.time.LocalDate;
import java.util.*;

public class BusService {
        Scanner input = new Scanner(System.in);
        Passenger loggedIn=null;
        String bookingId;
        double price;
        int bookings;
        ArrayList<Bookings> bookingList;
        ArrayList<Passenger> passengers;
        List<BusRoute> busRoutes;
        String Pass="Bus Management System/src/passengers.ser";
        String Route="Bus Management System/src/routes.ser";
        String Booking="Bus Management System/src/bookings.ser";
        public BusService() {
            passengers=(ArrayList<Passenger>)FileService.loadDetails(Pass);
            busRoutes=(ArrayList<BusRoute>)FileService.loadDetails(Route);
            bookingList=(ArrayList<Bookings>)FileService.loadDetails(Booking);
            if(passengers==null){
                passengers=new ArrayList<>();
            }
            if(busRoutes==null){
                busRoutes=new ArrayList<>();
            }
            if(bookingList==null){
                bookingList=new ArrayList<>();
            }
        }
        public void registerPassenger(Passenger passenger){
            if(passenger==null){
                System.out.println("Invalid passenger!");
                return;
            }
            for(Passenger p : passengers){
                if (p.getId() == passenger.getId()) {
                    System.out.println("ID already exists! Please Login.");
                    return;

                }
            }
            passengers.add(passenger);
            FileService.saveDetails(passengers, "Bus Management System/src/passengers.ser");
            System.out.println("Passenger registered successfully!");
        }
        public void login(){
            System.out.println("Login as Passenger--");
            System.out.println("Enter email: ");
            String email = input.nextLine();
            System.out.println("Enter password: ");
            String password = input.nextLine();
            for(Passenger p : passengers){
                if(p.getEmail().equals(email) && p.getPassword().equals(password)){
                    loggedIn=p;
                    System.out.println("Logged In as " + p.getName());
                    return;
                }
            }
            System.out.println("Wrong credentials! Register Yourself First if not done!");
            loggedIn=null;
        }
        public void addRoutes(BusRoute busRoute) {
            for (BusRoute route : busRoutes) {
                if (route.getRouteId() == busRoute.getRouteId()) {
                    System.out.println("Route Id already exists!");
                    return;
                }
            }

            busRoutes.add(busRoute);
            FileService.saveDetails(busRoutes, "Bus Management System/src/routes.ser");
            System.out.println("Route added successfully!");
        }

        public void searchBusRoutes(String source, String destination, LocalDate date) throws RouteNotFound {
            if(source==null || destination==null || date==null){
                throw new RouteNotFound("Route not found!");
            }
            boolean flag=false;
            for(BusRoute busRoute : busRoutes){
                if(busRoute.getFrom().equalsIgnoreCase(source) && busRoute.getTo().equalsIgnoreCase(destination) && busRoute.getDate().equals(date)){
                    System.out.println("Route found successfully!");
                    System.out.println("Route Id is: " + busRoute.getRouteId() + "\nFrom: " + busRoute.getFrom() + " To: " + busRoute.getTo() + "\nDate: " + busRoute.getDate() + "\nPrice: " + busRoute.getPrice() + "\nArrival Time is: " + busRoute.getArrivalTime() + " \nDeparture Time is: " + busRoute.getDepartureTime() + "\nAvailable Seats are: " + busRoute.getAvailableSeats());
                    flag=true;
                }
            }
            if(flag==false){
                throw new RouteNotFound("Route not found!");
            }
        }

        public void booking(int id,SeatBooking seatBooking) throws RouteNotFound, SeatNotAvailable {
           if(loggedIn==null){
               System.out.println("Passenger not registered! Register first if not done!");
               return;
           }
//            if (!loggedIn.getName().equalsIgnoreCase(seatBooking.getName()) || !loggedIn.getEmail().equalsIgnoreCase(seatBooking.getEmail()) || loggedIn.getPhone() != seatBooking.getPhone()) {
//
//                System.out.println("Booking failed: Details do not match the logged-in passenger!");
//                return;
//            }
           BusRoute foundRoute=null;
           for(BusRoute route : busRoutes){
               if(route.getRouteId()==id){
                   foundRoute=route;
                   System.out.println("Route Found Successfully!");
                   break;
               }
           }

           if(foundRoute==null){
               throw new RouteNotFound("Route not found!");
           }

           bookings=seatBooking.getNumberOfSeats();
           if(bookings<=0){
               System.out.println("Enter number greater than zero!");
               return;
           }
           if(bookings>foundRoute.getAvailableSeats()){
               throw new SeatNotAvailable("Not enough seats! Only "+foundRoute.getAvailableSeats()+" are available!");
           }

           foundRoute.setAvailableSeats(foundRoute.getAvailableSeats()-bookings);
           price=foundRoute.getPrice()*bookings;
           bookingId=seatBooking.generateId();
           Bookings bookings1 =new Bookings(bookingId, loggedIn.getId(), foundRoute.getRouteId(),foundRoute.getFrom(),foundRoute.getTo(),foundRoute.getDate(),bookings, price, Bookings.Status.CONFIRMED);
           bookingList.add(bookings1);
           FileService.saveDetails(bookingList, "Bus Management System/src/bookings.ser");
           System.out.println("Booking Confirmed Successfully!");
           System.out.println("Available Seats are : "+foundRoute.getAvailableSeats());
           System.out.println("Booking Id: " + bookingId + "\nNo of seats booked are: " + bookings + "\nPrice: " + price + "\nName of Passenger is: "+ seatBooking.getName() + "\nPhone number of passenger is: " + seatBooking.getPhone() + "\nRoute is from " + foundRoute.getFrom() + " to " + foundRoute.getTo() + " on date " + foundRoute.getDate());
        }

        public void viewBooking(String id) throws BookingNotFound{
            boolean flag=false;
            if(bookingList.isEmpty()){
                System.out.println("No bookings found!");
                return;
            }
            for(Bookings bookings : bookingList){
                if(loggedIn==null){
                    System.out.println("Passenger not registered! Register first if not done!");
                    return;
                }

                if(bookings.getBookingId().equals(id) && bookings.getPassengerId() == loggedIn.getId()){
                    flag=true;
                    System.out.println("Booking id is: "+bookings.getBookingId());
                    System.out.println("Passenger id is: "+bookings.getPassengerId());
                    System.out.println("Route id is: "+bookings.getRouteId());
                    System.out.println("Passenger is travelling from : "+bookings.getFrom() + " to : "+bookings.getTo() + " on date: " +  bookings.getDate());
                    System.out.println("Total Seats booked are: " + bookings.getSeatnumbers());
                    System.out.println("Total Price is: " + bookings.getTotalprice());
                    System.out.println("Booking Status is; " + bookings.getStatus());
                }
            }
            if(flag==false){
                throw new BookingNotFound("Booking not found!");
            }

        }

        public void cancelBooking(String id) throws BookingNotFound{
            Bookings foundBooking=null;
            for(Bookings bookings : bookingList){
                if(bookings.getBookingId().equals(id)){
                    foundBooking=bookings;
                    break;
                }
            }
            if(foundBooking==null){
                throw new BookingNotFound("Booking not found!");
            }

            if(foundBooking.getPassengerId()!=loggedIn.getId()){
                throw new BookingNotFound("You can cancel your booking only!");
            }

            if(foundBooking.getStatus()==Bookings.Status.CANCELLED){
                System.out.println("Booking Already Cancelled!");
                return;
            }
            for(BusRoute route : busRoutes){
                if(route.getRouteId()==foundBooking.getRouteId()){
                    route.setAvailableSeats(route.getAvailableSeats()+foundBooking.getSeatnumbers());
                }
            }
            foundBooking.setStatus(Bookings.Status.CANCELLED);
            System.out.println("Booking Cancelled Successfully!");

        }
        public void viewAllBookings(){
            if(bookingList.isEmpty()){
                System.out.println("No bookings found!");
                return;
            }
            int i=0;
            for(Bookings bookings : bookingList){
                System.out.println("Booking id is: "+bookings.getBookingId());
                System.out.println("Passenger id is: "+bookings.getPassengerId());
                System.out.println("Route id is: "+bookings.getRouteId());
                System.out.println("Passenger is travelling from : "+bookings.getFrom() + " to : "+bookings.getTo() + " on date: " +  bookings.getDate());
                System.out.println("Total Seats booked are: " + bookings.getSeatnumbers());
                System.out.println("Total Price is: " + bookings.getTotalprice());
                System.out.println("Booking Status is: " + bookings.getStatus());
            }
        }

        public void viewStatistics(){
            int totalBookings=bookingList.size();
            int cancelledBookings=0;
            double revenue=0.0;
            for(Bookings bookings : bookingList){
                if(bookings.getStatus()==Bookings.Status.CANCELLED){
                    cancelledBookings++;
                } else if (bookings.getStatus()==Bookings.Status.CONFIRMED) {
                    revenue+=bookings.getTotalprice();
                }
            }
            System.out.println("Total Bookings are: "+totalBookings);
            System.out.println("Cancelled Bookings are: "+cancelledBookings);
            System.out.println("Revenue generated is: "+revenue);
        }

    public void searchBookingAsAdmin(LocalDate date) throws BookingNotFound {
        for (Bookings b : bookingList) {
            if (b.getDate().equals(date)) {
                System.out.println("Booking id is: "+b.getBookingId());
                System.out.println("Passenger id is: "+b.getPassengerId());
                System.out.println("Route id is: "+b.getRouteId());
                System.out.println("Passenger is travelling from : "+b.getFrom() + " to : "+b.getTo() + " on date: " +  b.getDate());
                System.out.println("Total Seats booked are: " + b.getSeatnumbers());
                System.out.println("Total Price is: " + b.getTotalprice());
                System.out.println("Booking Status is; " + b.getStatus());
                return;
            }
        }
        throw new BookingNotFound("Booking not found!");
    }


}
