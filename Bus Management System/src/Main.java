import exceptions.BookingNotFound;
import exceptions.RouteNotFound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static int readInt(Scanner scanner,String Message)
    {
        int input;
        while(true){
            System.out.println(Message);
            try {
                input = scanner.nextInt();
                return input;
            }
            catch (InputMismatchException e){
                System.out.println("Enter numbers only!");
                scanner.nextLine();
            }
        }

    }
    public static double readDouble(Scanner scanner,String Message)
    {
        double input;
        while(true){
            System.out.println(Message);
            try {
                input = scanner.nextDouble();
                return input;
            }
            catch (InputMismatchException e){
                System.out.println("Enter numbers only!");
            }
        }

    }
    public static LocalDate DateInput(String message) {
        Scanner input = new Scanner(System.in);
        LocalDate d = null;
        String date;
        DateTimeFormatter dtf;
        while(true){
            System.out.println(message);
            try {
                dtf = DateTimeFormatter.ofPattern("dd-MM-yy");
                date=input.nextLine();
                d = LocalDate.parse(date,dtf);
                return d;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Enter in dd-MM-yy format!");
            }
        }
    }

    public static LocalTime TimeInput(String message) {
        Scanner input = new Scanner(System.in);
        LocalTime time=null;
        String dtime;
        DateTimeFormatter dtf;
        while(true){
            System.out.println(message);
            try {
                dtf = DateTimeFormatter.ofPattern("HH:mma");
                dtime=input.nextLine();
                time = LocalTime.parse(dtime,dtf);
                return time;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Time! Enter in HH:mm format!");
            }
        }
    }
    public static void main(String[] args) {
        Passenger passenger=null;
        BusRoute busRoute=null;
        SeatBooking seatBooking=null;
        BusService service=new BusService();
        Scanner input = new Scanner(System.in);
        boolean admin=true;
        System.out.println("---------------------------------------");
        System.out.println("|    Welcome to Bus Booking System    |");
        System.out.println("---------------------------------------");
        while(admin==true){
            System.out.println("Login as: ");
            System.out.print("1. Admin\n"+ "2. Passenger\n"+ "3. LogOut\n");
            //System.out.println("Enter your choice: ");
            int choice = readInt(input,"Enter your choice: ");
            input.nextLine();
            switch (choice) {
                case 1:{
                    System.out.println("Enter Admin name: ");
                    String name = input.nextLine();
                    System.out.println("Enter Password: ");
                    String password = input.nextLine();
                    if(!name.equals("admin") || !password.equals("12345")){
                        System.out.println("Wrong credentials !");
                        break;
                    }
                    boolean adminChoice=true;
                    while(adminChoice==true){
                        System.out.println("---ADMIN MENU---");
                        System.out.print("1. Add new bus routes\n"+
                                         "2. View All Bookings\n"+
                                         "3. View booking statistics\n"+
                                         "4. Search Bookings\n"+
                                         "5. LogOut\n");
                        //System.out.println("Enter your choice: ");
                        int achoice = readInt(input,"Enter your choice: ");
                        switch (achoice) {
                            case 1:{
                                try{
                                    int id=readInt(input,"Enter route Id: ");
                                    input.nextLine();
                                    System.out.println("Enter from route:");
                                    String from = input.nextLine();
                                    System.out.println("Enter to route:");
                                    String to = input.nextLine();
                                    LocalTime dt=TimeInput("Enter arrival time:");
                                    LocalTime at=TimeInput("Enter departure time:");
                                    LocalDate d=DateInput("Enter Date: ");
                                    int seats=readInt(input,"Enter total number of seats: ");
                                    double price=readDouble(input,"Enter price:");
                                    busRoute=new BusRoute(id,from,to,at,dt,d,seats,price);
                                    service.addRoutes(busRoute);
                                }
                                catch(RouteNotFound e){
                                    System.out.println(e.getMessage());
                                }
                                adminChoice=true;
                                break;
                            }
                            case 2:{
                                service.viewAllBookings();
                                adminChoice=true;
                                break;
                            }
                            case 3:{
                                service.viewStatistics();
                                adminChoice=true;
                                break;
                            }
                            case 4:{
                                try {
                                    LocalDate searchDate=DateInput("Enter Date to search: ");
                                    service.searchBookingAsAdmin(searchDate);
                                } catch (BookingNotFound e) {
                                    System.out.println(e.getMessage());
                                }
                                adminChoice=true;
                                break;
                            }
                            case 5:{
                                System.out.println("Logged out as admin!");
                                adminChoice=false;
                                break;
                            }
                            default:{
                                System.out.println("Wrong choice! Enter valid number!");
                                adminChoice=true;
                            }
                        }
                    }
                    admin=true;
                    break;
                }
                case 2:{
                    boolean passReg=true;
                    while(passReg==true){
                        System.out.print("1. Register\n2. Login\n"+"3. Go to menu\n"+"4. Exit\n");
                        //System.out.println("Enter your choice: ");
                        int prchoice = readInt(input,"Enter your choice: ");
                        switch (prchoice) {
                            case 1:{
                                int ID=readInt(input,"Enter Passenger ID: ");
                                input.nextLine();
                                System.out.println("Enter Passenger name: ");
                                String pname= input.nextLine();
                                System.out.println("Enter email id: ");
                                String email=input.nextLine();
                                System.out.println("Enter phone number: ");
                                long phone=input.nextLong();
                                input.nextLine();
                                System.out.println("Enter Passenger password: ");
                                String pass = input.nextLine();
                                passenger=new Passenger(ID,pname,email,phone,pass);
                                service.registerPassenger(passenger);
                                passReg=true;
                                break;
                            }
                            case 2:{
                                input.nextLine();
                                service.login();
                                passReg=true;
                                break;
                            }
                            case 3:{
                                boolean passChoice=true;
                                while(passChoice==true){
                                    System.out.print("1. Search for Buses\n"+
                                            "2. Book Tickets\n"+
                                            "3. View Booking\n"+
                                            "4. Cancel Booking\n"+
                                            "5. Exit\n");
                                    //System.out.println("Enter your choice: ");
                                    int pchoice = readInt(input,"Enter your choice: ");
                                    input.nextLine();
                                    switch (pchoice) {
                                        case 1:{
                                            try {
                                                System.out.println("Enter the source route: ");
                                                String source = input.nextLine();
                                                System.out.println("Enter the destination route: ");
                                                String destination = input.nextLine();
                                                LocalDate d = DateInput("Enter Date");
                                                service.searchBusRoutes(source,destination,d);
                                            }
                                            catch(RouteNotFound e){
                                                System.out.println(e.getMessage());
                                            }
                                            passChoice=true;
                                            break;
                                        }
                                        case 2:{
                                            try {
                                                int id = readInt(input,"Enter routeId: ");
                                                int seats =readInt(input,"Enter number of seats: ");
                                                input.nextLine();
                                                System.out.println("Enter your name ");
                                                String name = input.nextLine();
                                                System.out.println("Enter your email id: ");
                                                String email = input.nextLine();
                                                System.out.println("Enter your phone number: ");
                                                long phone = input.nextLong();
                                                seatBooking = new SeatBooking(seats, name, email, phone);
                                                service.booking(id, seatBooking);
                                            }
                                            catch (RouteNotFound e){
                                                System.out.println(e.getMessage());
                                            }
                                            passChoice=true;
                                            break;
                                        }
                                        case 3:{
                                            try {
                                                System.out.println("Enter your booking Id:");
                                                String id = input.nextLine();
                                                service.viewBooking(id);
                                            }catch (BookingNotFound e){
                                                System.out.println(e.getMessage());
                                            }
                                            passChoice=true;
                                            break;
                                        }
                                        case 4:{
                                            try {
                                                System.out.println("Enter your booking Id:");
                                                String id = input.nextLine();
                                                service.cancelBooking(id);
                                            }
                                            catch (BookingNotFound e){
                                                System.out.println(e.getMessage());
                                            }
                                            passChoice=true;
                                            break;
                                        }
                                        case 5:{
                                            System.out.println("Exited Successfully!");
                                            passChoice=false;
                                            break;
                                        }
                                        default:{
                                            System.out.println("Wrong choice! Enter valid number!");
                                            passChoice=true;
                                        }
                                    }
                                }
                                passReg=true;
                                break;
                            }
                            case 4:{
                                System.out.println("Exited Successfully as passenger!");
                                passReg=false;
                                break;
                            }
                            default:{
                                System.out.println("Wrong choice! Enter valid number!");
                                passReg=true;
                            }


                        }
                    }
                    admin=true;
                    break;
                }
                case 3:{
                    System.out.println("----------------------------------------------------");
                    System.out.println("|    Thankyou for using BusRoute Booking System    |");
                    System.out.println("----------------------------------------------------");
                    admin=false;
                    break;
                }
                default:{
                    System.out.println("Wrong choice! Enter valid number!");
                    admin=true;
                }
            }
        }

    }
}