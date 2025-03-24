package group.proj.User;
import group.proj.Air_port.Airport;
import group.proj.Air_port.Flight;

import java.util.List;

public class Admin extends person {

    public int id;

    public Admin(String username, String password, int age, String gender, String phone, String email, String ssn, List<Admin> AdminsNum) {

        super(username, password, age, gender, phone, email, ssn);
        if (AdminsNum.toArray().length > 0){
            this.id = AdminsNum.getLast().id + 1;
        }else{

            this.id = 1;
        }

    }

    public int getAdminID() {
        return id;
    }


}


