package kth.intnet.projekt.model;

import android.content.Context;
import android.os.TransactionTooLargeException;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Ludde on 2014-03-03.
 */
public class MoneyModel extends Observable{
    private User currentUser;
    private CountryList countries;
    private Gson gson;

    public MoneyModel(Context context){
        gson = new Gson();
        fillCountryList(context);
    }

    /**
     * Make an asynchronous call to the server to instantiate the country list
     * @param context
     */
    private void fillCountryList(Context context){
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getCurrencies");
        try{
            String result = sTask.get();
            countries = gson.fromJson(result, CountryList.class);
        } catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        notifyObservers("User changed");
        this.currentUser = currentUser;
    }

    public void createNewTransaction(int fromUser, int toUser, float amount, String fromCurr, String type){
        Transaction t = new Transaction(fromUser, toUser, amount, fromCurr, type);
        // Add transaction in database
        notifyObservers("Transaction added");
    }

    public void addNewUser(String fname, String lname, String username, String password, String country, String email){

    }

    public List<Transaction> getAllTransactions(int userId){
        // retrieve all transactions made to or from this user
        return null;
    }

    public User loginUser(String username, String password){
        return null;
    }

    public List<User> getAllUsers(){
        // retrieve all users in database
        return null;
    }

    public CountryList getCountries(){
        return countries;
    }

    public void updateUser(int userId){

    }

    public void doTransfer(String fromUser, String toUser, float amount, String fromCurr, String type){

    }

    public String getCurrency(String getCountry){
        // retrieve the currency related to this country from database
        return null;
    }
}
