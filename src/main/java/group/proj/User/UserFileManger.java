package group.proj.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserFileManger {

    public List<Passenger> passengers;
    public List<Admin>admins;

    public List<Admin> getAdmins() {
        return admins;
    }

    private   String userfilePath;
    private String adminfilePath;


    public UserFileManger(String userfilePath, String adminfilePath) {
        this.userfilePath = userfilePath;
        this.passengers = new ArrayList<>();
        this.adminfilePath=adminfilePath;
        this.admins=new ArrayList<>();
    }
    // Add a new passenger to the list
    public void addUser(Passenger passenger) {
        passengers.add(passenger);
    }
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    // Save all passengers to the JSON file
    public void saveUsers() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(userfilePath)) {
            gson.toJson(passengers, writer);
            System.out.println("Users saved to: " + userfilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void saveAdmins() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(adminfilePath)) {
            gson.toJson(admins, writer);
            System.out.println("Users saved to: " + adminfilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load passengers from the JSON file
    public void loadUsers() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(userfilePath)) {
            Type listType = new TypeToken<List<Passenger>>() {}.getType();
            passengers = gson.fromJson(reader, listType);
            System.out.println("Users loaded from: " + userfilePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty user list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadAdmins() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(adminfilePath)) {
            Type listType = new TypeToken<List<Admin>>() {}.getType();
            admins = gson.fromJson(reader, listType);
            System.out.println("Admin loaded from: " + adminfilePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty user list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //=================================
    public int getnumeofusers(){

        int num= passengers.toArray().length;

        return num;
    }
    public int getnumeofadmins(){

        if(this.admins==null){
            return 0;

        }else {
            int num = admins.toArray().length;

            return num;
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}

