package group.proj.Air_port;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Flight {

    private int flightId;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;
    private double Firstclass_price;
    private double business_price;
    private double eco_price;
    private int numOfAvailableSeats;
    private boolean flights_available;
    private int numOfSeats;
    private List<Seat> seats; // List of seats for this flight


    public void make_seats(char start,char end,int startnum,int endnum,seat_class seatClass, List<Seat> seats){

        for (int row=startnum; row<=endnum; row++) {
            for (char col=start; col<=end; col++) {
                seats.add(new Seat(row+String.valueOf(col),seatClass,seats.toArray().length ));
            }
        }
    }
    public void makeSeats( ) {

        this.seats = new ArrayList<>();
        make_seats('A','C',1,5,seat_class.FIRST,this.seats);
        make_seats('D','F',1,10,seat_class.BUSINESS,this.seats);
        make_seats('G','O',1,15,seat_class.ECONOMY,this.seats);

    }
    public double getFirstclass_price() {
        return Firstclass_price;
    }
    public double getBusiness_price() {
        return business_price;
    }
    public double getEco_price() {
        return eco_price;
    }

    public Flight(List<Flight> flights, Airport departureAirport, Airport arrivalAirport, String depatureTime, String arrivalTime, String departureDate, String arrivalDate,double firstclass_price,double eco_price ,double business_price) {

        if (flights.toArray().length > 0){
        this.flightId = flights.getLast().flightId + 1;
        }else{
            this.flightId = 1;
        }
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = depatureTime;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.Firstclass_price=firstclass_price;
        this.business_price=business_price;
        this.eco_price=eco_price;
        this.flights_available = true;
        this.numOfSeats=180;
        this.numOfAvailableSeats=180;
        makeSeats();

    }

    public int getFlightId() { return flightId; }
    public Airport getDepartureAirport() { return departureAirport; }
    public Airport getArrivalAirport() { return arrivalAirport; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public boolean isFlights_available() {
        return flights_available;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public String getDepartureDate() { return departureDate; }
    public String getArrivalDate() { return arrivalDate; }
    public int getNumOfAvailableSeats() { return numOfAvailableSeats; }
    public void bookaseat(){

        this.numOfAvailableSeats -= 1;
        if (numOfAvailableSeats<=0){
            this.flights_available = false;
        }

    }
    public String toString() {
        return flightId + " - " +  departureAirport.getCity() + "," + departureAirport.getCountry() + " - " + arrivalAirport.getCity() + "," + arrivalAirport.getCountry() + " - " + departureTime  + " - " + arrivalTime  + " - " + departureDate + " - " + arrivalDate + " - " + Firstclass_price+"$" + " - " + business_price+"$" + " - " + eco_price+"$" ;
    }
}
