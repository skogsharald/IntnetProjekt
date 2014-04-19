package kth.intnet.projekt.model;

import android.content.Context;
import android.content.Intent;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.spec.ECField;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import kth.intnet.projekt.android.MenuActivity;

/**
 * Created by Ludde on 2014-03-03.
 */
public class MoneyModel {
    private User currentUser;
    private UserList uList;
    private TransactionList tList;
    private CountryList cList;
    private Gson gson;
    private Context context;

    public MoneyModel(Context context) {
        this.context = context;
        gson = new Gson();
    }

    /**
     * Make an asynchronous call to the server to instantiate the country list
     *
     * @param context
     */
    private CountryList fillCountryList(Context context) {
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getCountries");
        try {
            String result = sTask.get();
            CountryList countries = gson.fromJson(result, CountryList.class);
            return countries;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return null;
    }

    private UserList fillUserList(Context context) {
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getUsers");
        try {
            String res = sTask.get();
            Log.e("USERS REQUEST RESULT", res);
            UserList uList = gson.fromJson(res, UserList.class);
            if(uList.getUserList() == null){
                uList.setUserList(new User[0]);
            }
            return uList;
        } catch (Exception e) {
            Log.e("ERROR", "ERROR", e);
        }
        return null;
    }

   private TransactionList fillTransactionList(Context context, int userId){
       ServerTask sTask = new ServerTask(context);
       sTask.execute("getTransactions", String.valueOf(userId));
       try {
           String res = sTask.get();
           TransactionList tList = gson.fromJson(res, TransactionList.class);
           if(tList.getTransactionList() == null){
               tList.setTransactionList(new Transaction[0]);
           }
           return tList;
       } catch (Exception e) {
           Log.e("ERROR", e.getMessage());
       }
       return null;
   }

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * User has been logged in or logged out
     * @param currentUser
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getUser(int userId){
        for(User u: uList.getUserArrayList()){
            if(u.getId() == userId){
                return u;
            }
        }
        return null;
    }

    public String createNewTransaction(int fromUser, String toUser, float amount) {
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getUsers");

        User[] uArray = uList.getUserList();
        Log.e("USERS", Arrays.toString(uArray));
        boolean userFound = false;
        int toUserId = 0;
        String toUserCountry = null;
        for(User u: uArray){
            if(u.getUsername().toLowerCase().equals(toUser.toLowerCase())){
                toUserId = u.getId();
                toUserCountry = u.getCountry();
                Log.e("USER TO GET CASH", String.valueOf(toUserId));
                userFound = true;
            }
        }

        if(!userFound){
            return "ERROR: Recipient not found";
        } else {

            // Find currency from and currency to
            User currentUser = getCurrentUser();
            CountryList cList = getCountries();
            String fromCurr = null;
            String toCurr = null;
            for (Country c : cList.getCountryList()) {
                if (c.getCountryName().equals(currentUser.getCountry())) {
                    fromCurr = c.getCurrency();
                }
                if(c.getCountryName().equals(toUserCountry)){
                    toCurr = c.getCurrency();
                }
            }
            if (fromCurr == null) {
                return "ERROR: User country not found";
            }


            // Make transaction in database
            ServerTask sTask2 = new ServerTask(context);
            try {
                // Find transaction rate
                String rateString = getTransferRate(fromCurr, toCurr);
                if(rateString == null){
                    return "ERROR: Transfer failed. Check your network connection.";
                }
                float rate = Float.parseFloat(rateString);

                sTask2.execute("newTransaction", String.valueOf(currentUser.getId()), String.valueOf(toUserId), String.valueOf(amount), fromCurr, "Outgoing", String.valueOf(rate));
                String result = sTask2.get();
                Log.e("RESULT FROM TRANSFER", result);
                String toastText;
                if (!result.contains("ERROR")) {
                    // Add transaction in model
                    // TODO: What happens when client can't connect to server?
                    Transaction t = new Transaction(fromUser, toUserId, amount, fromCurr, "Outgoing", rate, new Timestamp(Calendar.getInstance().getTimeInMillis()).toString().split("\\.")[0]);
                    currentUser.setBalance(currentUser.getBalance()-amount);
                    tList.addTransaction(t);
                    toastText = "Transfer successful!";
                } else {
                    toastText = "ERROR: Transfer failed. Check your parameters and network connection.";
                }
                return toastText;
            } catch(Exception e){
                Log.e("ERROR", "ERROR", e);
            }
        }
        return null;
    }

    public String addNewUser(String fname, String lname, String username, String password1, String country, String email) {
        ServerTask sTask = new ServerTask(context);
        sTask.execute("addUser", fname, lname, username, password1, country, email);
        try {
            String res = sTask.get();
            if(res.contains("ERROR")){
                return res;
            } else {
                ServerTask sTask2 = new ServerTask(context);
                sTask2.execute("loginUser", username, password1);
                String res2 = sTask2.get();
                User newUser = gson.fromJson(res2, User.class);
                // Add the new user in model and log in
                uList.addUser(newUser);
                loginUser(newUser.getUsername(), newUser.getPassword());
                return "Success";
            }
        } catch (Exception e){
            Log.e("ERROR", e.toString());
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return tList.getTransactionArrayList();
    }

    /**
     * Sign in user and load from server
     * @param username
     * @param password
     * @return
     */
    public boolean loginUser(String username, String password) {

        ServerTask sTask = new ServerTask(context);
        sTask.execute("loginUser", username, password);
        try {
            String result = sTask.get();
            if (result.contains("ERROR")) {
                return false;
            } else {
                User newUser = gson.fromJson(result, User.class);
                setCurrentUser(newUser);
                uList = fillUserList(context);
                uList.convertToArrayList();
                tList = fillTransactionList(context, this.currentUser.getId());
                tList.convertListToArrayList();
                cList = fillCountryList(context);
                Log.e("RESULT", result);
                return true;
            }
        } catch (Exception e) {
            Log.e("ERROR", "ERROR", e);
        }
        return false;
    }


    public List<User> getAllUsers(){
        // retrieve all users in database
        return null;
    }

    public CountryList getCountries(){
        return cList;
    }

    public UserList getUsers() { return uList; }

    public void updateUser(int userId){

    }

    public String getCurrency(String getCountry){
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getUserCurrency", getCountry);
        try{
            String res = sTask.get();
            return res;
        } catch(Exception e){
            Log.e("ERROR", "ERROR", e);
        }
        return null;
    }

    public String getTransferRate(String fromCurr, String toCurr){
        ServerTask sTask = new ServerTask(context);
        sTask.execute("getTransferRate", fromCurr, toCurr);
        try{
            String res = sTask.get();
            return res;
        } catch (Exception e){
            Log.e("ERROR", "ERROR", e);
        }
        return null;
    }
}
