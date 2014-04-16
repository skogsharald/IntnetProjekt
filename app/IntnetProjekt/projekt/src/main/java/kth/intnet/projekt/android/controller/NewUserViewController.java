package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kth.intnet.projekt.android.view.NewUserView;
import kth.intnet.projekt.model.Country;
import kth.intnet.projekt.model.CountryList;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;

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
     * Fill spinner with an asynchronous call to server
     */
    private void fillSpinner(){

    }

    /**
     * Clicking the create user button
     * @param view
     */
    @Override
    public void onClick(View view) {

    }
}
