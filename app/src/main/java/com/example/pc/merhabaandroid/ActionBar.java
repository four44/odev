package com.example.pc.merhabaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();

        switch (id)
        {
            case R.id.paylas:
                //islemler
                break;
            case R.id.wifi:
                //islemler
                break;
            case R.id.hakkinda:
                //islemler
                break;
            case R.id.action_settings:
                //islemler
                break;

        }
        return true;
    }

}
