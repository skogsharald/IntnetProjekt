package kth.intnet.projekt.android.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import kth.intnet.projekt.R;
import kth.intnet.projekt.model.MoneyModel;

/**
 * Created by Sandra Grosz on 2014-03-25.
 */
public class MenuView {
    private View view;
    private Activity activity;
    private MoneyModel model;
    public Button listTransfersButton, makeTransferButton;

    public MenuView(View view, Activity activity, MoneyModel model){
        this.view = view;
        this.activity = activity;
        this.model = model;

        // Instantiate the view
        listTransfersButton = (Button) activity.findViewById(R.id.listTransfersButton);
        makeTransferButton = (Button) activity.findViewById(R.id.makeTransferButton);
    }
}
