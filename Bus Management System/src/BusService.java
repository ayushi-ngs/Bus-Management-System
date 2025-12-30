import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BusService {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> ID = new HashSet<>();
        ArrayList<Passenger> passengers = new ArrayList<>();
        public void addPassenger(Passenger passenger){

            if(passenger==null){
                System.out.println("Invalid passenger!");
                return;
            }
            for(Passenger p : passengers){
                if(ID.contains(p.getId())){
                    System.out.println("ID already exists! Go back and Login!");
                }
            }
            passengers.add(passenger);
        }




}
