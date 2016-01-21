package org.canite.pimp3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by austin on 9/29/15.
 */
public class MP3Activity extends Activity {
    private static String logtag = "helloworld";

    Button connectButton, clearButton, playButton, stopButton;
    TextView textResponse;
    EditText editTextAddress, editTextPort;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPort = (EditText)findViewById(R.id.port);
        editTextAddress.setText("192.168.1.122");
        editTextPort.setText("20000");
        textResponse = (TextView)findViewById(R.id.response);

        connectButton = (Button)findViewById(R.id.connectButton);
        connectButton.setOnClickListener(connectListener);

        clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(clearListener);

        stopButton = (Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(stopListener);

        playButton = (Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(playListener);
    }

    private View.OnClickListener connectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - connectListener");
            //Toast.makeText(MainActivity.this, "The connect button was clicked", Toast.LENGTH_LONG).show();
            MyClientTask myClientTask = new MyClientTask(
                    editTextAddress.getText().toString(),
                    Integer.parseInt(editTextPort.getText().toString()),
                    "1"
            );
            myClientTask.execute();

            Log.d(logtag, "onClick() ended - connectListener");
        }
    };

    private OnClickListener playListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - playListener");
            //Toast.makeText(MainActivity.this, "The stop button was clicked", Toast.LENGTH_LONG).show();
            MyClientTask myClientTask = new MyClientTask(
                    editTextAddress.getText().toString(),
                    Integer.parseInt(editTextPort.getText().toString()),
                    "2"
            );
            myClientTask.execute();
            Log.d(logtag, "onClick() ended - playListener");
        }
    };

    private OnClickListener stopListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - stopListener");
            //Toast.makeText(MainActivity.this, "The stop button was clicked", Toast.LENGTH_LONG).show();
            MyClientTask myClientTask = new MyClientTask(
                    editTextAddress.getText().toString(),
                    Integer.parseInt(editTextPort.getText().toString()),
                    "3"
            );
            myClientTask.execute();
            Log.d(logtag, "onClick() ended - stopListener");
        }
    };

    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - clearListener");
            textResponse.setText("");
            Log.d(logtag, "onClick() ended - clearListener");
        }
    };

}
