package group.proj.Air_port;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class FlightsManager {

    private List<Flight> flights;
    private List<Airport> airports;
    private   String FlightfilePath;
    private String AirportfilePath;

    public List<Airport> getAirports() {
        return airports;
    }

    public FlightsManager(String Flightfilepath, String AirportfilePath) {
        flights = new ArrayList<>();
        airports = new ArrayList<>();
        this.FlightfilePath=Flightfilepath;
        this.AirportfilePath=AirportfilePath;
    }
    // Method to read flights from a JSON file
    public void loadFlightsFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FlightfilePath)) {

            Type listType = new TypeToken<List<Flight>>(){}.getType();
            flights = gson.fromJson(reader,listType);
            System.out.println("Flights loaded successfully.");

        } catch (IOException e) {
            System.err.println("Error reading flights file: " + e.getMessage());
        }
    }

    // Method to read airports from a JSON file
    public void loadAirPortsFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(AirportfilePath)) {

            Type listType = new TypeToken<List<Airport>>(){}.getType();
            airports = gson.fromJson(reader,listType);
            System.out.println("Airports loaded successfully.");

        } catch (IOException e) {
            System.err.println("Error reading airports file: " + e.getMessage());
        }
    }

    public void saveFlights() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FlightfilePath)) {
            gson.toJson(flights, writer);
            System.out.println("Flights saved to: " + FlightfilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAirports() {

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(AirportfilePath)) {
            gson.toJson(airports, writer);
            System.out.println("Users saved to: " + AirportfilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add an airport manually
    public void addAirPort(Airport airPort) {
            this.airports.add(airPort);
    }

    public void addFlight(Flight flight){
        this.flights.add(flight);
    }

    // Getters for the lists
    public List<Flight> getFlights() {
        return flights;
    }

    public List<Airport> getAirPorts() {
        return airports;
    }



    public int getnumberofFlights(){

        if(this.flights==null){
            return 0;

        }else {
            int num = flights.toArray().length;
            return num;
        }
    }

    public int getnumberofairports(){

        if(this.airports==null){
            return 0;

        }else {
            int num = airports.toArray().length;
            return num;
        }
    }
}
