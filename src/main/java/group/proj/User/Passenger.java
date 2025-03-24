package group.proj.User;
import group.proj.Booking_Sys.Booking;
import java.util.ArrayList;
import java.util.List;
public class Passenger extends person{

    private final int passngerId;
    private List<Booking> bookings = new ArrayList<>();



    public Passenger(String username, String password, int age, String gender, String phone, String email, String ssn, int Usersnum) {

        super(username, password, age, gender, phone, email, ssn);
        this.bookings = new ArrayList<>();
        this.passngerId = Usersnum+1;

    }

    public void bookflight(Booking booking) {
        this.bookings.add(booking);
    }

    public void updateInformation(String newName, int newAge, String newGender, String newPhone, boolean save) {
        if (newAge <= 0) {
            System.out.println("Age must be greater than 0.");
            return;
        }

        if (!newGender.equalsIgnoreCase("Male") && !newGender.equalsIgnoreCase("Female")) {
            System.out.println("Gender must be 'Male' or 'Female'.");
            return;
        }

        if (newPhone.length() != 11) {
            System.out.println("Phone number must be 11 digits.");
            return;
        }

        if (save) {
            this.username = newName;
            this.age = newAge;
            this.gender = newGender;
            this.phone = newPhone;
            System.out.println("Passenger information updated successfully!");
        } else {
            System.out.println("Update cancelled. No changes were made.");
        }
    }

    public int getPassngerId() {
        return passngerId;
    }

    public int getNumOfBookings(){
        if(bookings==null){
            return 0;
        }else {
        int num = bookings.toArray().length;
        return num;
    }}
    public List<Booking> getBookings() {
        return bookings;
    }

}
