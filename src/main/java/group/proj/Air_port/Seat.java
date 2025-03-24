package group.proj.Air_port;

import group.proj.Booking_Sys.Ticket;

public class Seat {

    private int id;
    private String seatNumber;
    public seat_class getSeatClass() {
        return seatClass;
    }
    private boolean isAvailable;
    private seat_class seatClass;
    public int getId() {
        return id;
    }

    public Seat(String seatNumber, seat_class seatClass,int num) {
        this.seatNumber = seatNumber;
        this.seatClass=seatClass;
        this.isAvailable = true;
        this.id = num+1;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void bookSeat() {
        if (isAvailable) {
            this.isAvailable = false;
            System.out.println("Seat " + seatNumber + " booked successfully!");
        } else {
            System.out.println("Seat " + seatNumber + " is already booked!");
        }
    }

    public void cancelBooking() {
        if (!isAvailable) {
            System.out.println("Booking for Seat " + seatNumber + " has been cancelled.");
            this.isAvailable = true;
        } else {
            System.out.println("Seat " + seatNumber + " is already available.");
        }
    }
}
