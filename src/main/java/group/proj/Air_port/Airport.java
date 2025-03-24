package group.proj.Air_port;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Airport {

    //    private static int numberOfAirports = 0; // Shared across all Airport objects
    private int airPortCode;
    private String airPortName;
    private String City;
    private String Country;






    // Constructor
    public Airport(List<Airport> airports, String airPortName, String city, String country) {

        if (airports.toArray().length > 0){
            this.airPortCode = airports.getLast().airPortCode + 1;
        }else{
            this.airPortCode = 101;
        }

        this.airPortName = airPortName;
        this.City = city;
        this.Country = country;
    }

    // Getters and setters
    public int getAirPortCode() {
        return airPortCode;
    }
    public String getAirPortName() {
        return airPortName;
    }

    public String getCity() {
        return City;
    }
    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public void setAirPortCode(int airPortCode) {
        this.airPortCode = airPortCode;
    }
    public void setAirPortName(String airPortName) {
        this.airPortName = airPortName;
    }
    public void setCity(String city) {
        this.City = city;
    }

    public String toString() {

        return airPortCode + " - " +  airPortName + "-" + City + " , " +  Country;

    }
}
