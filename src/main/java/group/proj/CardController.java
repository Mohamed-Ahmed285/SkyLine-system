package group.proj;

import group.proj.Booking_Sys.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardController {

    @FXML
    private Label arr;

    @FXML
    private Label arr_date;

    @FXML
    private Label arr_time;

    @FXML
    private Label dep;

    @FXML
    private Label dep_date;

    @FXML
    private Label dep_time;

    @FXML
    private Label price;

    @FXML
    private Label seatclass;

    @FXML
    private Label seatid;

    @FXML
    private Label ticketid;

    public void setdata(Booking book){

        dep.setText(book.getBookedflight().getDepartureAirport().getCity());
        arr.setText(book.getBookedflight().getArrivalAirport().getCity());
        dep_date.setText(book.getBookedflight().getDepartureDate());
        arr_date.setText(book.getBookedflight().getArrivalDate());
        dep_time.setText(book.getBookedflight().getDepartureTime());
        arr_time.setText(book.getBookedflight().getArrivalTime());
        price.setText(String.valueOf(book.getPayment().getBaseFare()) + "$");
        seatclass.setText(String.valueOf(book.getBookedSeat().getSeatClass()));
        seatid.setText(book.getBookedSeat().getSeatNumber());
        ticketid.setText(book.getTicket().getTicket_number());


    }

}
