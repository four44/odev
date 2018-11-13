package com.example.pc.merhabaandroid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DosyaIndirmeServisi extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private int result = Activity.RESULT_CANCELED;
   public static final String LINK="link";
    public static final String DOSYA_ADI= "dosya_adi";

    // TODO: Rename parameters
    public static final String DOSYA_YOLU= "dosya_yolu";
    public static final String SONUC="sonuc";
    public static final String BILDIRIM= "com.example.pc.merhabaandroid";

    public DosyaIndirmeServisi() {
        super("DosyaIndirmeServisi");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
       String dosyaIndirmeAdresi= intent.getStringExtra(LINK);
       String dosyaAdi = intent.getStringExtra(DOSYA_ADI);
       File dosya= new File(Environment.getExternalStorageDirectory(), dosyaAdi);
       if(dosya.exists()){
           dosya.delete();
       }
       InputStream stream=null;
       FileOutputStream fos= null;

       try{
           URL url= new URL(dosyaIndirmeAdresi);
           stream=url.openConnection().getInputStream();
           InputStreamReader reader= new InputStreamReader(stream);
           fos =new FileOutputStream(dosya.getPath());
           int next= -1;
           while ((next=reader.read()) != -1) {
               fos.write(next);
           }
           result=Activity.RESULT_OK;
       } catch (Exception e){
           e.printStackTrace();
       } finally {
           if(stream!=null) {
               try{
                   stream.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
           if (fos!=null){
               try{
                   fos.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
       }
       sinyalGonder(dosya.getAbsolutePath(), result);
    }


   private void sinyalGonder(String outputPath, int result) {
        Intent intent= new Intent(BILDIRIM);
        intent.putExtra(DOSYA_YOLU, outputPath);
        intent.putExtra(SONUC, result);
        sendBroadcast(intent);
   }
}
