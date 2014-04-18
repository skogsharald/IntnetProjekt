package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import kth.intnet.projekt.android.MenuActivity;
import kth.intnet.projekt.android.NewUserActivity;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;
import kth.intnet.projekt.model.User;

/**
 * Created by Ludde on 2014-03-17.
 */
public class LoginViewController implements View.OnClickListener {
    private MoneyModel model;
    private LoginView view;
    private Activity activity;
    Gson gson;
    /**
     * Public constructor for the controller.
     */
    public LoginViewController(LoginView view, Activity activity, MoneyModel model){
        this.view = view;
        this.model = model;
        this.activity = activity;
        gson = new Gson();

        view.loginButton.setOnClickListener(this);
        view.createUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(view.loginButton)){
            // Login user
            String username = view.usernameField.getText().toString();
            String password = view.passwordField.getText().toString();
            if(username.length() != 0 && password.length() != 0) {
                boolean successful = model.loginUser(username, password);
                if(!successful){
                    Toast.makeText(activity.getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(activity, MenuActivity.class);
                    activity.startActivity(intent);
                    Toast.makeText(activity, "Login successful", Toast.LENGTH_SHORT).show();
                }

            }
        }
        if(v.equals(view.createUserButton)){
            // Create new user
            Intent intent = new Intent(activity, NewUserActivity.class);
            activity.startActivity(intent);
        }
    }
}
