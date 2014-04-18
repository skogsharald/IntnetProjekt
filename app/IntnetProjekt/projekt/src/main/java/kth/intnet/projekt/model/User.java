package kth.intnet.projekt.model;

/**
 * Created by Ludde on 2014-03-03.
 */
public class User {
    private int id;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private String email;
    private String country;
    private float balance;

    public User(){

    }
    public User(int id, String fName, String lName, String username, String password, String email, String country, float balance){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.balance = balance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBalance(float balance) { this.balance = balance; }

    public float getBalance() { return balance; }

    @Override
    public String toString(){
        return this.getId()+ ": " + this.getUsername();
    }
}
