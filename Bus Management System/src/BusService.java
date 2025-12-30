import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class BusService {
        Passenger passenger;
        Scanner input = new Scanner(System.in);
        HashSet<Integer> ID = new HashSet<>();
        ArrayList<Passenger> passengers = new ArrayList<>();
        public void registerPassenger(Passenger passenger){

            if(passenger==null){
                System.out.println("Invalid passenger!");
                return;
            }
            for(Passenger p : passengers){
                ID.add(p.getId());
                Iterator<Integer> it=ID.iterator();
                while(it.hasNext()) {
                    if (ID.contains(p.getId())) {
                        System.out.println("ID already exists! Go to Login!");
                        login();
                        return;
                    }
                }
            }
            passengers.add(passenger);
            System.out.println("Passenger registered successfully!");
        }
        public void login(){
            System.out.println("Login as Passenger--");
            System.out.println("Enter email: ");
            String email = input.nextLine();
            System.out.println("Enter password: ");
            String password = input.nextLine();
            for(Passenger p : passengers){
                if(!p.getEmail().equals(email) || !p.getPassword().equals(password)){
                    System.out.println("Wrong credentials! Register Yourself First if not done!");
                    return;
                }
                System.out.println("Logged In as " + p.getName());
            }
        }
}
