import java.util.UUID;

public class SeatBooking {
    private int numberOfSeats;
    private String name;
    private String email;
    private long phone;
    private String id;
    public SeatBooking(int numberOfSeats, String name, String email, long phone) {
        this.numberOfSeats = numberOfSeats;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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
    public String generateId() {
        String id = UUID.randomUUID().toString();
//        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
//        byteBuffer.putLong(id.getMostSignificantBits());
//        byteBuffer.putLong(id.getLeastSignificantBits());
//        return Base64.getUrlEncoder().withoutPadding().encodeToString(byteBuffer.array());
        return id;
    }
    public String getId() {
        String id= generateId();
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Name is: " + getName() + "\nEmail is: " + getEmail() + "\nPhone is: " + getPhone() + "\nTotal seats booked are: " + getNumberOfSeats() + "\nID is: " + getId();
    }


}
