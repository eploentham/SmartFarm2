package com.nakoyagarden.eploentham.smartfarm;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String site="";
    SmartFarmControl sfc;
    test1 aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnS1 = (Button)findViewById(R.id.btnSite1);
        final Button btnS2 = (Button)findViewById(R.id.btnSite2);
        final TextView tv = (TextView)findViewById(R.id.textView);
        sfc = (SmartFarmControl) getApplicationContext();
        aaa = new test1();
        btnS1.setText("ที่บ้าน");
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sf.siteId = "1";
                String url="http://58.8.70.62:8080/cgi-bin/testpython.py?devi1=Sensor1";
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
                        tv.setText(result);
                    }

                } catch (Exception e) {
                    Log.e("Praeda", e.getMessage());
                }
            }
        });
        btnS2.setText("ไร่เลิศรส");
        btnS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sf.siteId = "2";
                Intent s1 = new Intent(view.getContext(), MainView.class);
                startActivityForResult(s1, 0);
            }
        });
    }

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
