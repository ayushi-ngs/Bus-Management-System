import java.io.Serializable;

public class Passenger implements Serializable {
    private int id;
    private String name;
    private String email;
    private long phone;
    private String password;

    Passenger(int id, String name, String email, long phone,String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return "Passenger Details are:\nid=" + id + "\nname=" + name + "\nemail=" + email + "\nphone=" + phone;
    }
}
