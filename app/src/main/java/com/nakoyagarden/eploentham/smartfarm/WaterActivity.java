package com.nakoyagarden.eploentham.smartfarm;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WaterActivity extends AppCompatActivity {
    //private Spinner spinner1;
    private Switch sw1, sw2, sw3;
    private SeekBar sb1;
    private TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        //spinner1 = (Spinner) findViewById(R.id.spinner);
        sw1 = (Switch)findViewById(R.id.switch1);
        sw2 = (Switch)findViewById(R.id.switch2);
        sw3 = (Switch)findViewById(R.id.switch3);
        sb1 = (SeekBar)findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        //List<String> list = new ArrayList<String>();
        //list.add("Android");
        //list.add("Java");
        //list.add("Spinner Data");
        //list.add("Spinner Adapter");
        //list.add("Spinner Example");
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner1.setAdapter(dataAdapter);
        sw1.setText("ปั้มน้ำแปลงผักสลัด");
        sw2.setText("ปั้มพ่นหมอกแปลงผักสลัด");
        sw3.setText("ปั้มถังพ่นยา");
        sb1.setMax(80);
        tv1.setText("Tank1");
        tv2.setText("Tank2");

        String url="http://58.8.70.62:8080/cgi-bin/readgpioall.py";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyDialog()  //show a dialog
                .permitNetwork() //permit Network access
                .build());
        //String url="http://www.google.com";
        //Intent s1 = new Intent(view.getContext(), MainView.class);
        //startActivityForResult(s1, 0);
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response;
        try {
            response = httpclient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            // Examine the response status
            Log.i("Praeda", response.getStatusLine().toString());

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to worry about connection release

            if (entity != null) {

                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
                //tv.setText(result);
                result = result.replace("\n","");
                result = result.replace("\n","");
                result = result.replace("\n","");
                result = result.trim();
                String[] aa = result.split(",");
                if(aa.length>0){
                    if(aa[0].equals("1")){
                        sw2.setChecked(true);
                    }else{
                        sw2.setChecked(false);
                    }
                    if(aa[1].equals("1")){
                        sw3.setChecked(true);
                    }else{
                        sw3.setChecked(false);
                    }
                    if(aa[2].equals("1")){
                        sw1.setChecked(true);
                    }else{
                        sw1.setChecked(false);
                    }
                }

                //result.equals("") ? sw1.setChecked(true):sw1.setChecked(false);
            }

        } catch (Exception e) {
            Log.e("Praeda", e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_water, menu);
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
    private static String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
