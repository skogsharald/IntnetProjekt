package kth.intnet.projekt.android.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import kth.intnet.projekt.R;
import kth.intnet.projekt.model.Country;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class ListTransfersView {
    private View view;
    private Activity activity;
    private MoneyModel model;
    public TextView availableBalance, balanceCurrency;
    public LinearLayout includeTransfers;


    public ListTransfersView(View view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;

        availableBalance = (TextView) activity.findViewById(R.id.availableBalance);
        balanceCurrency = (TextView) activity.findViewById(R.id.balanceCurrency);
        includeTransfers = (LinearLayout) activity.findViewById((R.id.includeTransfers));



    }
}
