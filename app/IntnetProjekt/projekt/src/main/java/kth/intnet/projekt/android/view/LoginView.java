package kth.intnet.projekt.android.view;

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

    /**
     * Public constructor for the login view. Very basic, just sets up the view for controller.
     * @param view
     * @param model
     */
    public LoginView(View view, MoneyModel model) {
        this.view = view;
        this.model = model;

        // Instantiate the view
        loginButton = (Button) view.findViewById(R.id.loginButton);
        createUserButton = (Button) view.findViewById(R.id.createUserButton);

        usernameField = (EditText) view.findViewById(R.id.usernameField);
        passwordField = (EditText) view.findViewById(R.id.passwordField);
    }
}
