package kth.intnet.projekt.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import kth.intnet.projekt.R;
import kth.intnet.projekt.android.controller.LoginViewController;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;

public class LoginActivity extends Activity {
    private LoginView loginView;
    private MoneyModel model;
    private LoginViewController loginViewController;
    private ServerTask serverTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ((MoneyApplication) this.getApplication()).getModel();

        setContentView(R.layout.activity_login);

        loginView = new LoginView(findViewById(R.layout.activity_login), this, model);
        loginViewController = new LoginViewController(loginView, this, model);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
