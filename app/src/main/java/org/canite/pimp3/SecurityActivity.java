package org.canite.pimp3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by austin on 10/9/15.
 */
public class SecurityActivity extends Activity{
    private static String logtag = "helloworld";

    Button connectButton, clearButton;
    TextView textResponse;
    VideoView videoView;
    ProgressDialog pDialog;
    EditText editTextAddress, editTextPort;
    android.os.Handler customHandler;
    boolean updateNow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sercurity);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextAddress.setText("rtp://192.168.1.122:20000");
        textResponse = (TextView)findViewById(R.id.response);
        videoView = (VideoView)findViewById(R.id.videoView);
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Streaming Video");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        connectButton = (Button)findViewById(R.id.connectButton);
        connectButton.setOnClickListener(connectListener);
        clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(clearListener);

        /*
        customHandler = new android.os.Handler();
        customHandler.postDelayed(updateImage, 0);
        updateNow = false;
        */
    }

    /*
    private Runnable updateImage = new Runnable() {
        public void run() {
            if (updateNow) {
                GetImageTask imageTask = new GetImageTask(
                        editTextAddress.getText().toString(),
                        Integer.parseInt(editTextPort.getText().toString())
                );
                imageTask.execute();
            }
            customHandler.postDelayed(updateImage, 50);
        }
    };
    */

    private View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - connectListener");
            //Toast.makeText(MainActivity.this, "The connect button was clicked", Toast.LENGTH_LONG).show();
            /*MyClientTask myClientTask = new MyClientTask(
                    editTextAddress.getText().toString(),
                    Integer.parseInt(editTextPort.getText().toString()),
                    "6"
            );
            myClientTask.execute();
            */
            updateNow = true;
            pDialog.show();

            try {
                MediaController mediaController = new MediaController(SecurityActivity.this);
                mediaController.setAnchorView(videoView);

                Uri video = Uri.parse(editTextAddress.getText().toString());
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(video);
            } catch (Exception e) {
                pDialog.dismiss();
                Log.e("video", e.getMessage());
                e.printStackTrace();
            }

            videoView.requestFocus();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    pDialog.dismiss();
                    videoView.start();
                }
            });
            Log.d(logtag, "onClick() ended - connectListener");
        }
    };

    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - clearListener");
            textResponse.setText("");
            videoView.stopPlayback();
            Log.d(logtag, "onClick() ended - clearListener");
        }
    };

    /*
    private class GetImageTask extends AsyncTask<Void, Void, Void> {
        String address;
        int port;
        Bitmap image = null;

        GetImageTask(String addr, int prt) {
            address = addr;
            port = prt;
        }

        @Override
        protected Void doInBackground(Void... params) {
            URL piURL;
            String urlString = "http://" + address + "/cam.jpg";
            try {
                piURL = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection)piURL.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is, 8190);

                image = BitmapFactory.decodeStream(bis);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            imageView.setImageBitmap(image);
            super.onPostExecute(result);
        }
    }*/
}
