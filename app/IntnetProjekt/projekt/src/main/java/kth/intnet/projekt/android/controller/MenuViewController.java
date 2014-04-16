package kth.intnet.projekt.android.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import kth.intnet.projekt.android.ListTransferActivity;
import kth.intnet.projekt.android.MakeTransferActivity;
import kth.intnet.projekt.android.MenuActivity;
import kth.intnet.projekt.android.NewUserActivity;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.android.view.MenuView;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-03-25.
 */
public class MenuViewController implements View.OnClickListener {
    private MoneyModel model;
    private MenuView view;
    private Activity activity;
    /**
     * Public constructor for the controller.
     */
    public MenuViewController(MenuView view, Activity activity, MoneyModel model){
        this.view = view;
        this.model = model;
        this.activity = activity;

        view.listTransfersButton.setOnClickListener(this);
        view.makeTransferButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(view.listTransfersButton)){
            // List transfers
            Intent intent = new Intent(activity, ListTransferActivity.class);
            activity.startActivity(intent);
        }
        if(v.equals(view.makeTransferButton)){
            // Make transfers
            Intent intent = new Intent(activity, MakeTransferActivity.class);
            activity.startActivity(intent);
        }
    }
}
