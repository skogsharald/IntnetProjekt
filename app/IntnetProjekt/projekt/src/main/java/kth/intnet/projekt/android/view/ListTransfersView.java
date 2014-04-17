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

        availableBalance.setText("2000");

        String currency = "";
        String userCountry = model.getCurrentUser().getCountry();

        for(Country country: model.getCountries().getCountryList()){
            if(country.getCountryName().equals(userCountry)) {
                currency = country.getCurrency();
            }
        }

        balanceCurrency.setText(currency);

        LinearLayout aTransfer = (LinearLayout) View.inflate(activity.getBaseContext(), R.layout.a_transfer_view, null);
        includeTransfers.addView(aTransfer, 0);

        TextView sender = (TextView) aTransfer.findViewById(R.id.sender);
        TextView amount = (TextView) aTransfer.findViewById(R.id.transferAmount);
        TextView date = (TextView) aTransfer.findViewById(R.id.transferDate);
        TextView transferCurrency = (TextView) aTransfer.findViewById(R.id.transferCurrency);

        sender.setText("Sandra");
        amount.setText(300);
        date.setText("2014-04-18");
        transferCurrency.setText(currency);

    }
}
