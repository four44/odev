package com.example.pc.merhabaandroid;

import android.app.Activity;
import android.app.usage.ExternalStorageStats;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DosyaIndirmeEkrani extends Activity implements View.OnClickListener{

    private TextView textView;
    private Button baslat, durdur;
    private ImageView imge;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle= intent.getExtras();
            islemSonucu(bundle);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosya_indirme_ekrani);
        degerleriAta();
    }
    public void degerleriAta()
    {
        textView = (TextView) findViewById(R.id.status);
        baslat = (Button) findViewById(R.id.baslat);
        durdur= (Button) findViewById(R.id.durdur);
        imge=(ImageView) findViewById(R.id.IView);
        baslat.setOnClickListener(this);
        durdur.setOnClickListener(this);
        String path="storage/sdcard/tumblr_npfwn4akvY1rlhvpdo1_1280.jpg";
        imge.setImageDrawable(Drawable.createFromPath(path));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DosyaIndirmeServisi.BILDIRIM));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    public void onClick(View view){
        switch(view.getId())
        {
            case R.id.baslat:
                ServisiBaslat();
                break;
            case R.id.durdur:
                ServisiDurdur();
                break;
            default:
                break;
        }
    }
    public void ServisiBaslat()
    {
        Intent intent = new Intent(this,DosyaIndirmeServisi.class);
        intent.putExtra(DosyaIndirmeServisi.DOSYA_ADI, "tumblr_npfwn4akvY1rlhvpdo1_1280.jpg");
        intent.putExtra(DosyaIndirmeServisi.LINK, "https://78.media.tumblr.com/7d398468f79135fa56d948ba4eadb22f/tumblr_npfwn4akvY1rlhvpdo1_1280.jpg");
        startService(intent);
        textView.setText("Servis başlatıldı. Dosya indiriliyor... ");
    }

    public void ServisiDurdur()
    {
        Intent intent = new Intent(this, DosyaIndirmeServisi.class);
        stopService(intent);
        textView.setText("servis durduruldu ");
    }
    private void islemSonucu(Bundle bundle){
        if (bundle != null) {
            String string= bundle.getString(DosyaIndirmeServisi.DOSYA_YOLU);
            int resultCode= bundle.getInt(DosyaIndirmeServisi.SONUC);
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "indirme işlemi tamamlandı. Klasör: " + string, Toast.LENGTH_LONG).show();
                textView.setText("indirme işlemi tamamlandı. ");




            } else {
                Toast.makeText(this,"indirme başarısız. " , Toast.LENGTH_LONG).show();
                textView.setText("indirme Başarısız. ");
            }
        }
    }
}
