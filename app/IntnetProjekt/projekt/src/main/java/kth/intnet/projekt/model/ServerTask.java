package kth.intnet.projekt.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;

/**
 * Created by Ludde on 2014-03-06.
 */
public class ServerTask extends AsyncTask<String, Integer, String> {
    private ServerHandler serverHandler;
    private Context context;

    public ServerTask(Context context){
        this.context = context;
        try {
            serverHandler = new ServerHandler();
        } catch (MalformedURLException e) {
            Log.e("Error", e.getMessage());
        }
    }
    @Override
    protected String doInBackground(String... params) {
        return null;

    }
}
