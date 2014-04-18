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

    public MakeTransferViewController(MakeTransferView view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;

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
        Log.e("CURRENT USER BALANCE", String.valueOf(model.getCurrentUser().getBalance()));
        if(amount > model.getCurrentUser().getBalance()){
            Toast.makeText(activity.getApplicationContext(), "Amount is greater than balance", Toast.LENGTH_SHORT).show();
            return;
        }
        int currentUserId = model.getCurrentUser().getId();
        String result = model.createNewTransaction(currentUserId, recipient, amount);
        if(result.contains("ERROR")){
            Toast.makeText(activity.getApplicationContext(), result.replace("ERROR:", ""), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity.getApplicationContext(), "Transaction successful", Toast.LENGTH_SHORT).show();
            activity.finish();
        }

    }
}
