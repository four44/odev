package com.example.pc.merhabaandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Async extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        ((Button) findViewById(R.id.indir)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DosyaIndir().execute();
            }
        });
    }
    public class DosyaIndir extends AsyncTask<Void, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = progressDialog.show(Async.this, "Bekleyin", "Dosya Indiriliyor");
        }

        @Override
        protected String doInBackground(Void... voids) {
        String dosyaIndirmeAdresi = "https://78.media.tumblr.com/7d398468f79135fa56d948ba4eadb22f/tumblr_npfwn4akvY1rlhvpdo1_1280.jpg";
        String dosyaAdi= "tumblr_npfwn4akvY1rlhvpdo1_1280.jpg";
        String indirilenDosya=null;
        File dosya = new File(Environment.getExternalStorageDirectory(), dosyaAdi);
            InputStream stream =null;
            FileOutputStream fos = null;
            try {
                URL url = new URL(dosyaIndirmeAdresi);
                stream=url.openConnection().getInputStream();
                InputStreamReader reader =new InputStreamReader(stream);
                fos=new FileOutputStream(dosya.getPath());
                int next =-1;
                while ((next= reader.read()) != -1){
                    fos.write(next);

                }
                indirilenDosya=dosyaAdi;
            }catch (Exception e) {
                Log.e("Hata: " , e.getMessage());
            }finally {
                if (stream!=null) {
                    try{
                        stream.close();
                    }catch (IOException e) {
                        Log.e("Hata: " , e.getMessage());

                    }
                }
                if (fos !=null) {
                    try{
                        fos.close();
                    }catch (IOException e){
                        Log.e("Hata: " , e.getMessage());
                    }
                }
            }
            return indirilenDosya;

        }

        @Override
        protected void onPostExecute(String indirilenDosya) {
            super.onPostExecute(indirilenDosya);
            progressDialog.dismiss();
            if(indirilenDosya!=null){
                String mesaj=indirilenDosya+ " isimli dosya indirildi. ";
                Toast.makeText(Async.this, mesaj, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Async.this, "Bir sorun oluştu ve dosya indirilemedi " , Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            progressDialog.dismiss();
            Toast.makeText(Async.this, "Bir sorun oluştu ve dosya indirilemedi", Toast.LENGTH_SHORT).show();
        }
    }
}
