package com.nakoyagarden.eploentham.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        sfc = (SmartFarmControl) getApplicationContext();
        aaa = new test1();
        btnS1.setText("ที่บ้าน");
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sf.siteId = "1";
                Intent s1 = new Intent(view.getContext(), MainView.class);
                startActivityForResult(s1, 0);
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
}
