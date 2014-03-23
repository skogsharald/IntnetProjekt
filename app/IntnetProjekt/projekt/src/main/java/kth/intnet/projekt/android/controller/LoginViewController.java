package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import kth.intnet.projekt.android.LoginActivity;
import kth.intnet.projekt.android.MoneyApplication;
import kth.intnet.projekt.android.NewUserActivity;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Ludde on 2014-03-17.
 */
public class LoginViewController implements View.OnClickListener {
    private MoneyModel model;
    private LoginView view;
    private Activity activity;
    /**
     * Public constructor for the controller.
     */
    public LoginViewController(LoginView view, Activity activity, MoneyModel model){
        this.view = view;
        this.model = model;
        this.activity = activity;

        view.loginButton.setOnClickListener(this);
        view.createUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(view.loginButton)){
            // Login user
        }
        if(v.equals(view.createUserButton)){
            // Create new user
            Intent intent = new Intent(activity, NewUserActivity.class);
            activity.startActivity(intent);
        }
    }
}
