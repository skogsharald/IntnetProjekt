package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kth.intnet.projekt.R;
import kth.intnet.projekt.android.view.ListTransfersView;
import kth.intnet.projekt.model.Country;
import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.Transaction;
import kth.intnet.projekt.model.TransactionList;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class ListTransferViewController {
    private MoneyModel model;
    private ListTransfersView view;
    private Activity activity;
    private TextView availableBalance,balanceCurrency;
    private LinearLayout includeTransfers;
    private String currency;
    /**
     * Public constructor for the controller.
     */
    public ListTransferViewController(ListTransfersView view, Activity activity, MoneyModel model){
        this.view = view;
        this.model = model;
        this.activity = activity;

        availableBalance = view.availableBalance;
        balanceCurrency = view.balanceCurrency;
        includeTransfers = view.includeTransfers;


        availableBalance.setText(String.valueOf(model.getCurrentUser().getBalance()));

        for(Country country: model.getCountries().getCountryList()){
            if(country.getCountryName().equals(model.getCurrentUser().getCountry())) {
                currency = country.getCurrency();
            }
        }

        balanceCurrency.setText(currency);
        loadTransfers();
    }

    public void loadTransfers(){
        ArrayList<Transaction> tList = (ArrayList<Transaction>) model.getAllTransactions();
        Log.e("TRANSACTIONS TO BE LISTED", tList.toString());

        if(tList.isEmpty()){
            LinearLayout aTransfer = (LinearLayout) View.inflate(activity.getBaseContext(), R.layout.a_transfer_view, null);
            includeTransfers.addView(aTransfer, 0);
            TextView sender = (TextView) aTransfer.findViewById(R.id.sender);
            TextView amount = (TextView) aTransfer.findViewById(R.id.transferAmount);
            TextView date = (TextView) aTransfer.findViewById(R.id.transferDate);
            TextView transferCurrency = (TextView) aTransfer.findViewById(R.id.transferCurrency);
            sender.setText("No transactions have been made to or from this account yet.");
            amount.setText("");
            date.setText("");
            transferCurrency.setText("");
        }
        for(Transaction t: tList){
            LinearLayout aTransfer = (LinearLayout) View.inflate(activity.getBaseContext(), R.layout.a_transfer_view, null);
            includeTransfers.addView(aTransfer, 0);

            TextView sender = (TextView) aTransfer.findViewById(R.id.sender);
            TextView amount = (TextView) aTransfer.findViewById(R.id.transferAmount);
            TextView date = (TextView) aTransfer.findViewById(R.id.transferDate);
            TextView transferCurrency = (TextView) aTransfer.findViewById(R.id.transferCurrency);

            String type = t.getType();
            if(type.equals("Outgoing") && t.getFromUser() == model.getCurrentUser().getId()){
                sender.setText("To: "+model.getUser(t.getToUser()).getUsername());
            } else {
                sender.setText("From: "+model.getUser(t.getFromUser()).getUsername());
            }
            amount.setText(String.valueOf(t.getAmount()));
            date.setText(t.getDate().toString());
            transferCurrency.setText(t.getFromCurr());
        }
    }
}
