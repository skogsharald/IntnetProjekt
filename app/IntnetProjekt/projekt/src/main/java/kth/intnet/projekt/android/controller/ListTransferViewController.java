package kth.intnet.projekt.android.controller;

import android.app.Activity;

import kth.intnet.projekt.android.view.ListTransfersView;
import kth.intnet.projekt.android.view.LoginView;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class ListTransferViewController {
    private MoneyModel model;
    private ListTransfersView view;
    private Activity activity;
    /**
     * Public constructor for the controller.
     */
    public ListTransferViewController(ListTransfersView view, Activity activity, MoneyModel model){
        this.view = view;
        this.model = model;
        this.activity = activity;
    }
}
