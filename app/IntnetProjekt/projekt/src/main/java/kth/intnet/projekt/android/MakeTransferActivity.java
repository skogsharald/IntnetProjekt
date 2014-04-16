package kth.intnet.projekt.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import kth.intnet.projekt.R;
import kth.intnet.projekt.android.view.MakeTransferView;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class MakeTransferActivity extends Activity{
    private MakeTransferView makeTransferView;
    private MoneyModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ((MoneyApplication) this.getApplication()).getModel();

        setContentView(R.layout.activity_make_transfers);

        makeTransferView = new MakeTransferView(findViewById(R.layout.activity_make_transfers), this, model);
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
