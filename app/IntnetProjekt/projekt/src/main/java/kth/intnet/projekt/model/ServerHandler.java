package kth.intnet.projekt.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;


/**
 * Created by Ludde on 2014-03-03.
 */
public class ServerHandler {
    private Context context;
    private int readTimeOut;
    private int connectTimeOut;
    private Gson gson;

    /**
     * This is where the connection with the server actually occurs.
     * Remember to change "localhost" to the server's IP.
     * @throws MalformedURLException
     */
    public ServerHandler() throws MalformedURLException {
//      this.context = context;
        readTimeOut = 2000;
        connectTimeOut = 3000;
    }

    public String loginUser(String username, String password) throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/login_user/username="+username+"&password="+password);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Login failed", sb.toString());
                return null;
            }
            Log.e("Login", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String addUser(String fname, String lname, String username, String password, String country, String email) throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/add_user/fname="+fname+"&lname="+lname+"&username="+
                username+"&password="+password+"&country="+country+"&email="+email);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Adding user failed", sb.toString());
                return null;
            }
            Log.e("Adding user", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String newTransaction(String fromUser, String toUser, float amount, String fromCurr, String type) throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/do_transfer/fromuser="+fromUser+"&touser="+toUser+
                "&amount="+amount+"&fromcurr="+fromCurr+"&type="+type);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Transaction failed", sb.toString());
                return null;
            }
            Log.e("Transaction", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String getUsers() throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/get_users/");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Something went wrong", sb.toString());
                return null;
            }
            Log.e("Users", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String getTransactions() throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/get_transfers/");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Something went wrong", sb.toString());
                return null;
            }
            Log.e("Users", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String updateUser(int userId, String fname, String lname, String username, String password, String country, String email) throws IOException {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/update_user/userid=" + userId + "fname=" + fname + "&lname=" + lname + "&username=" +
                username + "&password=" + password + "&country=" + country + "&email=" + email);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if (response == 400) {
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            if (sb.toString().contains("ERROR")) {
                Log.e("Updating user failed", sb.toString());
                return null;
            }
            Log.e("Updating user", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String getUserCurrency(String country) throws IOException  {
        InputStream is = null;
        URL url = new URL("http://localhost:8888/get_user_currency/country="+country);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(readTimeOut /* milliseconds */);
            conn.setConnectTimeout(connectTimeOut /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 400){
                return null;
            }
            is = conn.getInputStream();

            // Convert the InputStream into a string
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            br.close();
            if(sb.toString().contains("ERROR")){
                Log.e("Something went wrong", sb.toString());
                return null;
            }
            Log.e("Users", sb.toString());
            return sb.toString();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}