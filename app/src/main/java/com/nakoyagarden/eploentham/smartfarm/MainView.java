package com.nakoyagarden.eploentham.smartfarm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainView extends AppCompatActivity {
    Button btnWaterView, btnSensorView, btnDashBoard;
    String site="";
    SmartFarmControl sfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        sfc = (SmartFarmControl) getApplicationContext();
        btnWaterView = (Button)findViewById(R.id.btnWaterView);
        btnSensorView = (Button)findViewById(R.id.btnSensorView);
        btnDashBoard = (Button)findViewById(R.id.btnDashBoard);
        btnWaterView.setText(btnWaterView.getText()+" "+sfc.siteId);
        if(sfc.siteId.equals("1")){
            //RelativeLayout rl = (RelativeLayout)findViewById( );
            setTitle(getResources().getString(R.string.app_name)+" [ที่บ้าน]");
            //this.setBackgroundColor(0xFF00FF00);
        }else{
            setTitle(getResources().getString(R.string.app_name)+" [ไร่เลิศรส]");
        }
        btnWaterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s1 = new Intent(view.getContext(), WaterActivity.class);
                startActivityForResult(s1,0);
            }
        });
        btnSensorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://58.8.70.62:8080/smartfarm/testphp.php";
                //String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=80223,USA&mode=json&units=metric&cnt=7";
                new AsyncHttpTask().execute(url);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_view, menu);
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
    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            Integer result = 0;
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
                urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
                urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
                urlConnection.setRequestMethod("GET");
                //urlConnection.connect();
                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode == 200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);

                    //parseResult(response);

                    result = 1; // Successful

                } else {
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                String err = e.getLocalizedMessage();
                //Log.d( e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }
    }
}
