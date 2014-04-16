package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import kth.intnet.projekt.android.LoginActivity;
import kth.intnet.projekt.android.MenuActivity;
import kth.intnet.projekt.android.MoneyApplication;
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
            String result = "hejsan";
            String username = view.usernameField.getText().toString();
            String password = view.passwordField.getText().toString();
            if(username != null && password != null) {
                ServerTask sTask = new ServerTask(activity.getApplicationContext(), result);
                sTask.execute("loginUser", username, password);
                try {
                    result = sTask.get();
                    if(result == null){
                        Toast.makeText(activity.getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        User newUser = gson.fromJson(result, User.class);
                        model.setCurrentUser(newUser);
                        Intent intent = new Intent(activity, MenuActivity.class);
                        activity.startActivity(intent);
                        Log.e("RESULT", result);
                    }
                } catch (Exception e){
                    Log.e("ERROR", e.getMessage());
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
