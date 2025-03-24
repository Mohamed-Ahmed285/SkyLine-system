package group.proj.Booking_Sys;
import group.proj.Air_port.Flight;
import group.proj.Air_port.Seat;
import group.proj.Air_port.seat_class;
import java.util.ArrayList;
import java.util.List;

public class Booking {


    private int bookingID;
    private String passenger;
    private Flight Bookedflight;
    private Seat BookedSeat;
    private String bookingStatus;
    private Payment payment;
    private Ticket ticket;

    public Booking(int numofuserbookings, String passenger, Flight flight) {
        this.bookingID = numofuserbookings+1;
        this.passenger = passenger;
        this.Bookedflight = flight;
        this.bookingStatus = "Pending";
        this.payment = null;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getPassenger() {
        return passenger;
    }

    public Flight getBookedflight() {
        return Bookedflight;
    }
    public void setBookedflight(Flight bookedflight) {
        Bookedflight = bookedflight;
    }

    public Seat getBookedSeat() {
        return BookedSeat;
    }

    public void setBookedSeat(Seat bookedSeat) {
        BookedSeat = bookedSeat;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", passenger=" + passenger +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }


}
