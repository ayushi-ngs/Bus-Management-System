import exceptions.RouteNotFound;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static LocalDate DateInput() {
        Scanner input = new Scanner(System.in);
        LocalDate d = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (d == null) {
            System.out.println("Please enter a valid date");
            String date = input.nextLine();
            try {
                d = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date !");
            }
        }
        return d;
    }
    public static void main(String[] args) {
        Passenger passenger=null;
        BusRoute busRoute=null;
        BusService service=new BusService();
        Scanner input = new Scanner(System.in);
        boolean admin=true;
        while(admin==true){
            System.out.println("Login as: ");
            System.out.print("1. Admin\n"+ "2. Passenger\n"+ "3. LogOut\n");
            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
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
                        System.out.println("Enter your choice: ");
                        int achoice=input.nextInt();
                        switch (achoice) {
                            case 1:{
                                try{
                                    System.out.println("Enter route Id:");
                                    int id=input.nextInt();
                                    System.out.println("Enter from route:");
                                    String from = input.nextLine();
                                    System.out.println("Enter to route:");
                                    String to = input.nextLine();
                                    System.out.println("Enter arrival time:");
                                    LocalTime at=null;
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                                    while(at==null) {
                                        System.out.println("Please enter a valid time!");
                                        String time = input.nextLine();
                                        try {
                                            at = LocalTime.parse(time, formatter);
                                        } catch (DateTimeParseException e) {
                                            System.out.println("Invalid Time !");
                                        }
                                    }
                                    System.out.println("Enter departure time:");
                                    LocalTime dt=null;
                                    DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                                    while(dt==null) {
                                        System.out.println("Please enter a valid time!");
                                        String dtime = input.nextLine();
                                        try {
                                            dt = LocalTime.parse(dtime, dtformatter);
                                        } catch (DateTimeParseException e) {
                                            System.out.println("Invalid Time !");
                                        }
                                    }
                                    System.out.println("Enter date:");
                                    LocalDate d=DateInput();
                                    System.out.println("Enter total number of seats:");
                                    int seats=input.nextInt();
                                    System.out.println("Enter price:");
                                    double price=input.nextDouble();
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
                                adminChoice=true;
                                break;
                            }
                            case 3:{
                                adminChoice=true;
                                break;
                            }
                            case 4:{
                                try {
                                    input.nextLine();
                                    System.out.println("Enter the source route: ");
                                    String source = input.nextLine();
                                    System.out.println("Enter the destination route: ");
                                    String destination = input.nextLine();
                                    System.out.println("Enter Date of booking: ");
                                    LocalDate d = DateInput();
                                    service.searchBusRoutes(source,destination,d);
                                }
                                catch(RouteNotFound e){
                                    System.out.println(e.getMessage());
                                }
                                adminChoice=true;
                                break;
                            }
                            case 5:{
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
                        System.out.println("Enter your choice: ");
                        int prchoice=input.nextInt();
                        switch (prchoice) {
                            case 1:{
                                System.out.println("Enter Passenger ID:");
                                int ID=input.nextInt();
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
                                    System.out.print("1.Search for Buses\n"+"2. Book Tickets\n"+"3. View Booking\n"+"4. Cancel Booking\n"+"5. Exit\n");
                                    System.out.println("Enter your choice: ");
                                    int pchoice=input.nextInt();
                                    switch (pchoice) {
                                        case 1:{
                                            System.out.println("Enter source city:");
                                            String source=input.nextLine();
                                            System.out.println("Enter destination city:");
                                            String destination=input.nextLine();
                                            System.out.println("Enter date:");

                                            passChoice=true;
                                            break;
                                        }
                                        case 2:{
                                            passChoice=true;
                                            break;
                                        }
                                        case 3:{
                                            passChoice=true;
                                            break;
                                        }
                                        case 4:{
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
                    System.out.println("Thankyou for using BusRoute Booking System");
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