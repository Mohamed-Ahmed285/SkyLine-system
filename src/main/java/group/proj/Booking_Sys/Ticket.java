package group.proj.Booking_Sys;

public class Ticket {

    private String ticket_number;
    private double total;
    private String booking_id;
    private String seatNumber;
    private String paymentID;

    public Ticket(String flight_number, double total, String booking_id, String seatNumber, String paymentID) {
        this.ticket_number = generateTicket_number(booking_id,seatNumber,flight_number);
        this.total = total;
        this.booking_id = booking_id;
        this.seatNumber = seatNumber;
        this.paymentID = paymentID;
    }

    public String generateTicket_number(String booking_id, String seatNumber, String flight_number) {
        return booking_id + "-" + seatNumber + "-" + flight_number;
    }

    void Check_ticket(String status, String booking_id, String seatNumber, String paymentID,String flight_number) {   //booking status so if it cancelled the seat become available
        switch (status) {
            case "Successful" -> {
                this.ticket_number = generateTicket_number(booking_id, seatNumber, flight_number);
                this.paymentID = paymentID;
            }
            case "Failed","Cancelled" -> this.ticket_number = "NO TICKET IS AVAILABLE";
            case "pending" -> {
                System.out.println("Pending Ticket");
                //booking function boolean
                Check_ticket(status, booking_id, seatNumber, paymentID, flight_number);
            }
        }
    }

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }

    public double getFare() {
        return total;
    }

    public void setFare(double fare) {
        this.total = fare;
    }


    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void displayTicketDetails() {
        System.out.println("Ticket Number: " + ticket_number);
        System.out.println("Fare: " + total);
        System.out.println("Booking ID: " + booking_id);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Payment ID: " + paymentID);
    }

}
