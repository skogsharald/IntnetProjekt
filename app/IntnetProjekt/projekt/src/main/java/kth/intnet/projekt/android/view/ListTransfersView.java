package kth.intnet.projekt.android.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kth.intnet.projekt.R;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-04-16.
 */
public class ListTransfersView {
    private View view;
    private Activity activity;
    private MoneyModel model;


    public ListTransfersView(View view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;

    }
}
