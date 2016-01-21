package org.canite.pimp3;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by austin on 10/9/15.
 */
public class GetImage extends AsyncTask<Void, Void, Void>{
    String address;
    int port;

    public GetImage(String IPAddress, int portNumber) {

    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
