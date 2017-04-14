package com.example.chema.qradmin;

/**
 * Created by chema on 25/02/2017.
 */
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.claudio.qradmin.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
   @Override
   public void onCreate(Bundle state){
       super.onCreate(state);

       setContentView(R.layout.camera);
       FrameLayout frame=(FrameLayout)findViewById(R.id.frame);

       mScannerView= new ZXingScannerView(this);
       frame.addView(mScannerView);
       mScannerView.startCamera();
   }

    @Override
    public void handleResult(Result result) {
        TextView ver=(TextView)findViewById(R.id.TX_mostrar);
        TextView edad=(TextView)findViewById(R.id.TX_edad);
        TextView cu=(TextView)findViewById(R.id.TX_curso);
        ImageView im=(ImageView)findViewById(R.id.IM_bi);
        ver.setText(result.getText());
        Handler handler=new Handler();
        base a=new base(result.getText());
        try {
            Alumno ab=a.execute().get();
            ab.calcularedad();
            ver.setText(ab.getNombre());
            if(ab.calcularedad()){
                MediaPlayer mediaPlayer=MediaPlayer.create(this, R.raw.sucess);
                mediaPlayer.start();
            }else{
                MediaPlayer mediaPlayer=MediaPlayer.create(this, R.raw.error);
                mediaPlayer.start();
            }

            im.setImageBitmap(ab.getImage());
            cu.setText(ab.getCurso());
            edad.setText(""+ab.edadcalculada());
            if(ab.edadcalculada()>=18){
                edad.setTextColor(0xFF2EFE2E);
            }else{
                edad.setTextColor(0xFFFF0040);
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(scanner.this);
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void onPause(){
        super.onPause();
        mScannerView.startCamera();
    }



}
