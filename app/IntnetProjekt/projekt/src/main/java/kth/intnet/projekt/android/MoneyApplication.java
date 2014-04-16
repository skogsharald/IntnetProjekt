package kth.intnet.projekt.android;

import android.app.Application;

import kth.intnet.projekt.model.MoneyModel;
import kth.intnet.projekt.model.ServerTask;

/**
 * Created by Ludde on 2014-03-03.
 */
public class MoneyApplication extends Application {
    private MoneyModel mModel;

    public void onCreate(){
        super.onCreate();
        mModel = new MoneyModel(getApplicationContext());
    }

    public MoneyModel getModel(){
      return mModel;
    }

    public void setModel(MoneyModel mModel){
        this.mModel = mModel;
    }
}
