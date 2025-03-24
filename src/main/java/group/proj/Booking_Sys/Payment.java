package group.proj.Booking_Sys;
import group.proj.Air_port.Flight;
import group.proj.Air_port.Seat;
import group.proj.User.Passenger;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.util.UUID;
public class Payment {

    private double baseFare;
    private double taxes;
    private double totalCost;
    private List<String> additionalServices;
    private double servicecost;
    private String PaymentId;
    String date = LocalDate.now().toString();
    //==================================

    //====================================
    String randomHash = UUID.randomUUID().toString().substring(0, 8);
    public double getTaxes() {
        return taxes;
    }
    public double getBaseFare() {
        return baseFare;
    }
    public Payment(Seat seat , Passenger passenger, Flight flight) {

        this.baseFare = calculateBaseFare(seat,flight);
        this.taxes = baseFare*0.1;
        this.additionalServices = new ArrayList<>();
        this.servicecost = 0;
        this.totalCost = calculateTotalCost();
        this.PaymentId = flight.getFlightId() + "-" + passenger.getPassngerId() + "-" +  date + "-" + randomHash;

    }

    private double calculateBaseFare(Seat seat, Flight flight){
        double basefare = switch (seat.getSeatClass()) {
            case FIRST -> flight.getFirstclass_price();
            case ECONOMY -> flight.getEco_price();
            case BUSINESS -> flight.getBusiness_price();
        };
        return basefare;
    }
    private double calculateTotalCost(){
        return baseFare + taxes + servicecost;
    }
    public double calculateServiceCost() {
        double servicesCost = 0;
        for (String service : additionalServices) {
           if (service.equalsIgnoreCase("Extra Luggage")) {
                servicesCost += 500.0;
            } else if (service.equalsIgnoreCase("Insurance")) {
                servicesCost += 800.0;
            }
        }
        this.totalCost = calculateTotalCost();
        return servicesCost;
    }
    public void addAdditionalService(String service) {
        additionalServices.add(service);
    }
    public String getPayment_id() {
        return PaymentId;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public List<String> getAdditionalServices() {
        return additionalServices;
    }

}
