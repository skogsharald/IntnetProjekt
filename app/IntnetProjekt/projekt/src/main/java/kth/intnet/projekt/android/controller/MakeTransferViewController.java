package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Arrays;

import kth.intnet.projekt.android.view.MakeTransferView;
import kth.intnet.projekt.model.Country;
import kth.intnet.projekt.model.CountryList;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;
import kth.intnet.projekt.model.User;
import kth.intnet.projekt.model.UserList;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class MakeTransferViewController implements View.OnClickListener{
    private MakeTransferView view;
    private Activity activity;
    private MoneyModel model;
    private Gson gson;

    public MakeTransferViewController(MakeTransferView view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;
        gson = new Gson();

        view.makeTransferButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String recipient = this.view.toField.getText().toString();
        float amount = Float.parseFloat(this.view.amountField.getText().toString());

        if(recipient.length() == 0 || amount <= 0){
            Toast.makeText(activity.getApplicationContext(), "Invalid transfer.", Toast.LENGTH_SHORT).show();
            return;
        }

        ServerTask sTask = new ServerTask(activity.getApplicationContext());
        sTask.execute("getUsers");
        try {
            String res = sTask.get();
            if(res.contains("ERROR")){
                Toast.makeText(activity.getApplicationContext(), res.replace("ERROR:", ""), Toast.LENGTH_SHORT).show();
                return;
            }
            UserList uList = gson.fromJson(res, UserList.class);
            User[] uArray = uList.getUserList();
            boolean userFound = false;
            for(User u: uArray){
                if(u.getUsername().toLowerCase().equals(recipient.toLowerCase())){
                    userFound = true;
                }
            }

            if(!userFound){
                Toast.makeText(activity.getApplicationContext(), "Recipient not found", Toast.LENGTH_SHORT).show();
                return;
            }

            // Find currency
            User currentUser = model.getCurrentUser();
            CountryList cList = model.getCountries();
            String curr = null;
            for(Country c: cList.getCountryList()){
                if(c.getCountryName().equals(currentUser.getCountry())) {
                    curr = c.getCurrency();
                }
            }
            if(curr == null){
                Toast.makeText(activity.getApplicationContext(), "User country not found", Toast.LENGTH_SHORT).show();
                return;
            }

            // Make transfer
            ServerTask sTask2 = new ServerTask(activity.getApplicationContext());
            sTask2.execute("newTransaction", String.valueOf(currentUser.getId()), recipient, String.valueOf(amount), curr, "Outgoing");
            String result = sTask2.get();
            Log.e("RESULT FROM TRANSFER", result);
            String toastText;
            if(!result.contains("ERROR")){
                toastText = "Transfer successful!";
            } else {
                toastText = "Transfer failed. Check your parameters and network connection.";
            }

            Toast.makeText(activity.getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            activity.finish();

        } catch (Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
    }
}
