package com.example.pc.merhabaandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;


public class MainActivity extends Activity implements View.OnClickListener{

    Button girisButonu;
    String kullaniciAdi , sifre;
    EditText girilenKullaniciAdi;
    EditText girilenSifre;

    public void AnaSayfayiAc()
    {
        Intent anaSayfa=new Intent(this, AnaSayfa.class);
        anaSayfa.putExtra("kullanıcıadı", kullaniciAdi);
        anaSayfa.putExtra("girilenkullanıcıadı" ,girilenKullaniciAdi.getText());
        startActivity(anaSayfa);
    }

    public  void Bildiri(){
        NotificationManager bildirimYoneticisi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent PIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification bildiri = new Notification.Builder(this)
                .setContentTitle("Yeni e-mailiniz var." + "deneme@gmail.com")
                .setContentText("merhaba nasılsın. sizi aradım fakat ulaşamadım.")
                .setSmallIcon(R.drawable.gmail)

                .setAutoCancel(true).build();

        bildirimYoneticisi.notify(0, bildiri);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        degerleriAta();
        girisButonu.setOnClickListener(this);
        Bildiri();

        }
    public void degerleriAta()
    {
        girisButonu =(Button) findViewById(R.id.btnGiris);
        girilenKullaniciAdi= (EditText) findViewById(R.id.kullaniciAdi);
        girilenSifre = (EditText) findViewById(R.id.sifre);
        kullaniciAdi = "Admin";
        sifre = "123456";
    }
    @Override
    public void onClick(View v) {
        if(!girilenKullaniciAdi.getText().toString().equals(kullaniciAdi) || !girilenSifre.getText().toString().equals(sifre))
            Uyari();
        else {
            Bilgilendir();
            AnaSayfayiAc();
        }

    }
    public void Uyari() {
        try {
            //uyari penceresi
            Builder uyariPenceresi = new Builder(this);
            // ıcon tanımlaması
            uyariPenceresi.setIcon(R.drawable.warning);
            // başlık tanımlaması
            uyariPenceresi.setTitle("Uyarı Mesajı");
            //mesaj tanımlaması
            uyariPenceresi.setMessage("Girilen kullanıcı adı veya şifre bilgisi yanlış. Bilgileri doğru girdiğinizden emin misiniz? ");
            //evet butonu
            uyariPenceresi.setPositiveButton("Evet" , new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){

                }
            });
            //hayır butonu
            uyariPenceresi.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //kapat butonu
            uyariPenceresi.setNeutralButton("kapat", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //düzenlenen uyarı penceresini oluştur ve göster.
            AlertDialog pencere = uyariPenceresi.create();
            pencere.show();
            } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void Bilgilendir()
    {
        Toast.makeText(this, "Girilen kullanıcı adı ve şifre doğru", Toast.LENGTH_LONG).show();
    }
}

