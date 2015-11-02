package com.nakoyagarden.eploentham.smartfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
}
