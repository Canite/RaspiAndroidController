package org.canite.pimp3;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by austin on 10/3/15.
 */
public class MyClientTask extends AsyncTask<Void, Void, Void> {
    String dstAddress;
    int dstPort;
    String response = "";
    String output = "";

    MyClientTask(String addr, int port, String out) {
        dstAddress = addr;
        dstPort = port;
        output = out;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Socket socket = null;
        OutputStreamWriter outputStream = null;

        try {
            socket = new Socket(dstAddress, dstPort);
            outputStream = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            //outputStream.writeByte(output);
            //outputStream.flush();
            outputStream.write(output);
            outputStream.flush();
            //while ((bytesRead = inputStream.read(buffer)) != -1) {
            //    byteArrayOutputStream.write(buffer, 0, bytesRead);
            //    response += byteArrayOutputStream.toString("UTF-8");
            //}
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = "UnknownHostException" + e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOException" + e.toString();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    response = "IOException when closing outputstream" + e.toString();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    response = "IOException when closing" + e.toString();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //textResponse.setText(response);
        super.onPostExecute(result);
    }
}