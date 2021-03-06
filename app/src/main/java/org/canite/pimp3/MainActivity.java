package org.canite.pimp3;


import java.net.Socket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String logtag = "helloworld";
    Button mp3Button, securityButton;
    Socket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp3Button = (Button)findViewById(R.id.mp3Button);
        mp3Button.setOnClickListener(mp3ButtonListener);

        securityButton = (Button)findViewById(R.id.securityButton);
        securityButton.setOnClickListener(securityButtonListener);
    }

    // Play Button Listener (action when you push button)
    private OnClickListener mp3ButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - mp3Listener");
            Intent myIntent = new Intent(v.getContext(), MP3Activity.class);
            startActivity(myIntent);
            Log.d(logtag, "onClick() ended - mp3Listener");
        }
    };

    private OnClickListener securityButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(logtag, "onClick() called - securityListener");
            Intent myIntent = new Intent(v.getContext(), SecurityActivity.class);
            startActivity(myIntent);
            Log.d(logtag, "onClick() ended - securityListener");
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}