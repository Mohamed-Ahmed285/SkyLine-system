package group.proj.User;
public class person {

    protected String username;
    protected String Password;
    protected String  email;
    protected int age;
    protected String gender;
    protected String phone;
    protected String ssn;

    public person(String username, String password, int age, String gender, String phone, String email, String ssn) {
        this.username = username;
        this.Password = password;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.ssn = ssn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSsn() {
        return ssn;
    }

}
