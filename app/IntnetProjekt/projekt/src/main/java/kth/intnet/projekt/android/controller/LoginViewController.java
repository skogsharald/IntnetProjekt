package kth.intnet.projekt.android.controller;

import android.view.View;

import kth.intnet.projekt.android.MoneyApplication;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Ludde on 2014-03-17.
 */
public class LoginViewController implements View.OnClickListener {
    private MoneyModel model;
    private LoginView view;
    /**
     * Public constructor for the controller.
     */
    public LoginViewController(LoginView view, MoneyModel model){
        this.view = view;
        this.model = model;

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
        }
    }
}
