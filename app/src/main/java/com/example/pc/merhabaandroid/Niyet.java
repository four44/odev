package com.example.pc.merhabaandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Niyet extends Activity  {
    ImageView img;
//   private static final int IMAGE_ACTION_CODE= 102;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        Bitmap bm = (Bitmap)b.get("data");
        img.setImageBitmap(bm);
    }

    TextView textView;
    String gelenDeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niyet);
        img= (ImageView)findViewById(R.id.resimGoster);

        textView=  findViewById(R.id.textvview);
        gelenDeger= getIntent().getExtras().getString("metin");
        textView.setText(gelenDeger);
        Button b= (Button)findViewById(R.id.resimCek);
        b.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,1);

            }
        });
    }
}
