package kth.intnet.projekt.android.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import kth.intnet.projekt.R;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-03-23.
 */
public class NewUserView {
    private  View view;
    private Activity activity;
    private MoneyModel model;
    public Button createAccountButton;
    public EditText fnameField, lnameField, emailField, createUsernameField, passwordOneField, passwordTwoField;
    public Spinner dropdown;

    public NewUserView(View view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;

        // Instantiate the view
        createAccountButton = (Button) activity.findViewById(R.id.createAccountButton);

        fnameField = (EditText) activity.findViewById(R.id.fnameField);
        lnameField = (EditText) activity.findViewById(R.id.lnameField);
        emailField = (EditText) activity.findViewById(R.id.emailField);
        createUsernameField = (EditText) activity.findViewById(R.id.createUsernameField);
        passwordOneField = (EditText) activity.findViewById(R.id.passwordOneField);
        passwordTwoField = (EditText) activity.findViewById(R.id.passwordTwoField);

        dropdown = (Spinner) activity.findViewById((R.id.countySpinner));
    }
}
