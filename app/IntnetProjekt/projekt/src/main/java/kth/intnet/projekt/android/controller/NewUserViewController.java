package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import kth.intnet.projekt.R;
import kth.intnet.projekt.android.MenuActivity;
import kth.intnet.projekt.android.view.MenuView;
import kth.intnet.projekt.android.view.NewUserView;
import kth.intnet.projekt.model.Country;
import kth.intnet.projekt.model.CountryList;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;
import kth.intnet.projekt.model.User;

/**
 * Created by Sandra Grosz on 2014-03-23.
 */
public class NewUserViewController implements View.OnClickListener{
    private NewUserView view;
    private Activity activity;
    private MoneyModel moneyModel;
    private Gson gson;

    public NewUserViewController(NewUserView view, Activity activity, MoneyModel moneyModel){
        this.view = view;
        this.activity = activity;
        this.moneyModel = moneyModel;
        this.gson = new Gson();

        view.createAccountButton.setOnClickListener(this);

        // Fill the countries list with data from database
        fillSpinner();
    }

    /**
     * Fill spinner countries that are in model
     */
    private void fillSpinner(){
        CountryList countries = moneyModel.getCountries();
        if(countries == null){
            Log.e("ERROR", "Countries is null");
            return;
        }
        List<String> spinnerList = new ArrayList<String>();
        for(Country c: countries.getCountryList()){
            spinnerList.add(c.getCountryName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getApplicationContext(), R.layout.spinner_item, spinnerList);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        Spinner spinner = (Spinner) activity.findViewById(R.id.countySpinner);
        spinner.setAdapter(adapter);
    }

    /**
     * Clicking the create user button
     * @param view
     */
    @Override
    public void onClick(View view) {
        String fname = this.view.fnameField.getText().toString();
        String lname = this.view.lnameField.getText().toString();
        String username = this.view.createUsernameField.getText().toString();
        String password1 = this.view.passwordOneField.getText().toString();
        String password2 = this.view.passwordTwoField.getText().toString();
        String country = this.view.dropdown.getSelectedItem().toString();
        String email = this.view.emailField.getText().toString();

        if(fname.length()==0 || lname.length()==0 || username.length()==0 || password1.length()==0 || password2.length()==0 || email.length()==0 ||country.length()==0){
            Toast.makeText(activity.getApplicationContext(), "All fields must be entered", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password1.equals(password2)){
            Toast.makeText(activity.getApplicationContext(), "Passwords didn't match", Toast.LENGTH_SHORT).show();
        } else {
            ServerTask sTask = new ServerTask(activity.getApplicationContext());
            sTask.execute("addUser", fname, lname, username, password1, country, email);
            try {
                String res = sTask.get();
                if(res.contains("ERROR")){
                    Toast.makeText(activity.getApplicationContext(), res.replace("ERROR:", ""), Toast.LENGTH_SHORT).show();
                } else {
                    ServerTask sTask2 = new ServerTask(activity.getApplicationContext());
                    sTask2.execute("loginUser", username, password1);
                    String res2 = sTask2.get();
                    User newUser = gson.fromJson(res2, User.class);
                    moneyModel.setCurrentUser(newUser);
                    Toast.makeText(activity.getApplicationContext(), "User successfully created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, MenuActivity.class);
                    activity.startActivity(intent);
                }
            } catch (Exception e){
                Log.e("ERROR", e.toString());
            }

        }

    }
}
