package com.nakoyagarden.eploentham.smartfarm;

import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    //private HorizontalGridView horizontalGridView;
    private Switch sw1, sw2, sw3, sw4;
    private SeekBar sb1;
    private TextView tv1,tv2, tv222, tv221,tv332, tv442, tv552, tv662;
    ImageView iv,iv22,iv33,iv44,iv55,iv66;

    HttpClient httpclient = new DefaultHttpClient();
    HttpResponse response;
    HttpGet httpget;
    String url="http://58.8.70.62:8080/cgi-bin/readgpioall.py";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        //spinner1 = (Spinner) findViewById(R.id.spinner);
        sw1 = (Switch)findViewById(R.id.switch1);
        sw2 = (Switch)findViewById(R.id.switch2);
        sw3 = (Switch)findViewById(R.id.switch3);
        sw4 = (Switch)findViewById(R.id.switch4);
        //sb1 = (SeekBar)findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.textView3);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv222 = (TextView) findViewById(R.id.textView222);
        tv332 = (TextView) findViewById(R.id.textView332);
        tv442 = (TextView) findViewById(R.id.textView442);
        tv552 = (TextView) findViewById(R.id.textView552);
        tv662 = (TextView) findViewById(R.id.textView662);
        iv = (ImageView)findViewById(R.id.imageView);
        iv22 = (ImageView)findViewById(R.id.imageView22);
        iv33 = (ImageView)findViewById(R.id.imageView33);
        iv44 = (ImageView)findViewById(R.id.imageView44);
        iv55 = (ImageView)findViewById(R.id.imageView55);
        iv66 = (ImageView)findViewById(R.id.imageView66);
        //List<String> list = new ArrayList<String>();
        //list.add("Android");
        //list.add("Java");
        //list.add("Spinner Data");
        //list.add("Spinner Adapter");
        //list.add("Spinner Example");
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner1.setAdapter(dataAdapter);
        sw1.setText("ปั้มน้ำแปลงผักสลัด ");
        sw2.setText("ปั้มพ่นหมอกแปลงผักสลัด ");
        sw3.setText("ปั้มถังพ่นยา ");
        sw4.setText("เปิดน้ำเข้าถัง ");
        //sb1.setMax(80);
        tv1.setText("ถังน้ำพ่นหมอกแปลงผักสลัด");
        tv2.setText("Tank2");
        tv222.setText("ถังน้ำประปา");
        tv332.setText("ถังน้ำแปลงผักสลัด1");
        tv442.setText("ถังน้ำแปลงผักสลัด2");
        tv552.setText("ถังน้ำแปลงเมล่อน1");
        tv662.setText("ถังน้ำแปลงเมล่อน2");
        tv222.setTextColor(Color.BLACK);
        tv332.setTextColor(Color.BLACK);
        tv442.setTextColor(Color.BLACK);
        tv552.setTextColor(Color.BLACK);
        tv662.setTextColor(Color.BLACK);
        //tv3.setTextColor(Color.RED);
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv1.setTextSize(18);
        tv2.setTextSize(18);
        tv222.setTextSize(18);
        tv332.setTextSize(18);
        tv442.setTextSize(18);
        tv552.setTextSize(18);
        tv662.setTextSize(18);
        tv222.setTextSize(18);
        sw1.setTextSize(18);
        sw2.setTextSize(18);
        sw3.setTextSize(18);
        sw4.setTextSize(18);
        //tv3.setVisibility(1);
        iv.setImageResource(R.mipmap.ic_tank6);
        iv22.setImageResource(R.mipmap.ic_tank6);
        iv33.setImageResource(R.mipmap.ic_tank6);
        iv44.setImageResource(R.mipmap.ic_tank6);
        iv55.setImageResource(R.mipmap.ic_tank6);
        iv66.setImageResource(R.mipmap.ic_tank6);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyDialog()  //show a dialog
                .permitNetwork() //permit Network access
                .build());
        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tv1.setText("aaaaaaaaaa");
            }
        });
        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chk="";
                if(sw2.isChecked()){
                    chk="1";
                }else{
                    chk="0";
                }

                url="http://58.8.70.62:8080/cgi-bin/opengpio.py?devi1=30&status="+chk;
                httpget = new HttpGet(url);
                try{
                    response = httpclient.execute(httpget);
                    int status = response.getStatusLine().getStatusCode();
                    Log.i("Praeda", response.getStatusLine().toString());
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = entity.getContent();
                        String result= convertStreamToString(instream);
                        instream.close();
                        result = result.replace("\n","");
                        result = result.replace("\n","");
                        result = result.replace("\n","");
                        result = result.trim();
                        if(result.equals("ok")){
                            sw2.setChecked(true);
                        }else{
                            sw2.setChecked(false);
                        }

                    }
                }catch(Exception e){

                }
            }
        });
        sw4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //String url="http://www.google.com";
        //Intent s1 = new Intent(view.getContext(), MainView.class);
        //startActivityForResult(s1, 0);

        httpget = new HttpGet(url);

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
                    if(!aa[4].equals("")){
                        String aaa="";
                        int x=80,y=100,z=0;
                        z=Integer.parseInt(aa[4]);
                        //z=Integer.parseInt("20");
                        double zz = (y/x)*z;
                        //double zz = (y/x);
                        //for(int i=0;i<(zz*2);i++){
                        //    aaa+=".";
                        //}
                        double zzz=0;
                        zzz = 100-zz;
                        if(zzz>=80){
                            //tv3.setBackgroundColor(Color.GREEN);
                            //tv3.setTextColor(Color.GREEN);
                            iv.setImageResource(R.mipmap.ic_tank6);
                        }else if(zzz<=70) {
                            iv.setImageResource(R.mipmap.ic_tank5);
                        }else if(zzz<=60) {
                            iv.setImageResource(R.mipmap.ic_tank44);
                        }else if(zzz<=50){
                            //tv3.setBackgroundColor(Color.RED);
                            //tv3.setTextColor(Color.RED);
                            iv.setImageResource(R.mipmap.ic_tank3);
                        }else if(zzz<=40) {
                            iv.setImageResource(R.mipmap.ic_tank2);
                        }else if(zzz<=30) {
                            iv.setImageResource(R.mipmap.ic_tank1);
                        }else if(zzz<=20) {
                            iv.setImageResource(R.mipmap.ic_tank0);
                        }
                        //tv3.setText(aaa);
                        //tv1.setText(aa[4]);
                        tv2.setText(String.valueOf(zzz)+"%");
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
