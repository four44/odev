package com.example.pc.merhabaandroid;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AnaSayfa extends Activity  implements AdapterView.OnItemClickListener{

    String[] menu ={"Kamera" , "Batarya ", "Dosya Indirme", "Download" , "Yoklama"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        ListView liste= (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.liste,
                R.id.sekme, menu);
        liste.setAdapter(adapter);
        liste.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent , View view, int position, long id){
        Intent baslat;
        switch (position)
        {
            case 0 :
                baslat =new Intent(this, Niyet.class);
                baslat.putExtra("metin", "Merhaba Android");
                startActivity(baslat);
                break;
            case 1 :
                baslat =new Intent();
                baslat.setAction("BATARYA_DURUMU");
                sendBroadcast(baslat);
                break;
            case 2:
                baslat=new Intent(this , DosyaIndirmeEkrani.class);
                startActivity(baslat);
                break;
            case 3:
                baslat= new Intent(this, Async.class);
                startActivity(baslat);
                break;
            case 4:
                baslat = new Intent(this, ActionBar.class);
                startActivity(baslat);
                break;


        }
    }
}
