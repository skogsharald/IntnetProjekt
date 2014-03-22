package kth.intnet.projekt.android.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kth.intnet.projekt.R;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Ludde on 2014-03-17.
 */
public class LoginView {
    public static Button loginButton, createUserButton;
    public static EditText usernameField, passwordField;
    private View view;
    private MoneyModel model;
    private Activity activity;

    /**
     * Public constructor for the login view. Very basic, just sets up the view for controller.
     * @param view
     * @param model
     */
    public LoginView(View view, Activity activity, MoneyModel model) {
        this.view = view;
        this.activity = activity;
        this.model = model;

        // Instantiate the view
        loginButton = (Button) activity.findViewById(R.id.loginButton);
        createUserButton = (Button) activity.findViewById(R.id.createUserButton);

        usernameField = (EditText) activity.findViewById(R.id.usernameField);
        passwordField = (EditText) activity.findViewById(R.id.passwordField);
    }
}
