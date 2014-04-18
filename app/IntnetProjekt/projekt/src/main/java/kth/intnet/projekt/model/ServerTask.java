package kth.intnet.projekt.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Ludde on 2014-03-06.
 */
public class ServerTask extends AsyncTask<String, Integer, String> {
    private ServerHandler serverHandler;
    private Context context;

    public ServerTask(Context context){
        this.context = context;
        try {
            serverHandler = new ServerHandler();
        } catch (MalformedURLException e) {
            Log.e("Error", e.getMessage());
        }
    }

    /**
     * This method handles all calls to server and returns result in JSON format:
     * Result from a user login call is that user in JSON format to use with User.java
     * Result from adding or updating a user, or performing a transaction is just a success string.
     * Result from getting users is a JSON list of users, use UserList.java for this
     * Result from getting transactions is a JSON list of transactions, user TransactionList.java for this
     * Result from getting a user currency is a string containing the three-letter code for that currency
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        try {
            String method = params[0];
            if (method.equals("loginUser")) {
                // Do login user
                String username = params[1];
                String password = params[2];
                String result = serverHandler.loginUser(username, password);
                if(result != null) {
                    return result;
                }
            } else if (method.equals("addUser")) {
                // Do add user
                String fname = params[1];
                String lname = params[2];
                String username = params[3];
                String password = params[4];
                String country = params[5];
                String email = params[6];
                String result = serverHandler.addUser(fname, lname, username, password, country, email);
                if (result != null) {
                    return result;
                }
            } else if (method.equals("getCountries")){
                // Get all countries
                String result = serverHandler.getCountries();
                if(result != null) {
                    return result;
                }
            } else if (method.equals("newTransaction")) {
                // Do new transaction
                String fromUser = params[1];
                String toUser = params[2];
                float amount = Float.parseFloat(params[3]);
                String fromCurr = params[4];
                String type = params[5];
                float rate = Float.parseFloat(params[6]);
                String result = serverHandler.newTransaction(fromUser, toUser, amount, fromCurr, type, rate);
                if(result != null) {
                    return result;
                }
            } else if (method.equals("getUsers")) {
                // Get all users
                String result = serverHandler.getUsers();
                if(result != null) {
                    return result;
                }
            } else if (method.equals("getTransactions")) {
                String userId = params[1];
                // Get all transactions
                String result = serverHandler.getTransactions(userId);
                if(result != null) {
                    return result;
                }
            } else if (method.equals("getTransferRate")){
                String fromCurr = params[1];
                String toCurr = params[2];
                String result = serverHandler.getTransferRate(fromCurr, toCurr);
                if( result != null ){
                    return result;
                }
            } else if (method.equals("getUserCurrency")) {
                String country = params[1];
                // Get user currency
                String result = serverHandler.getUserCurrency(country);
                if(result != null){
                    return result;
                }
            } else if (method.equals("updateUser")) {
                int userId = Integer.parseInt(params[1]);
                String fname = params[2];
                String lname = params[3];
                String username = params[4];
                String password = params[5];
                String country = params[6];
                String email = params[7];
                // Update user
                String result = serverHandler.updateUser(userId, fname, lname, username, password, country, email);
                if(result != null){
                    return result;
                }
            }
        } catch(IOException e){
            Log.e("Error in server communication", "ERROR", e);

        }
        return null;

    }
}
