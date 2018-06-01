package com.hv.remote.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hv.remote.R;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PreviewImageActivity extends Activity {
    public final String TAG = this.getClass().getSimpleName();
    public final static String CONTROLER_POSITION = "/remoteCamera/controler/";
    ImageView previewImage = null;
    Intent intent = null;
    private String fileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        intent = getIntent();
        fileName = intent.getType();
        Log.e(TAG,"fileName:"+fileName);
        setContentView(R.layout.myimageview);

        previewImage = (ImageView) findViewById(R.id.activity_preview_image);

        FileInputStream f = null;
        try {
            f = new FileInputStream(Environment.getExternalStorageDirectory() + CONTROLER_POSITION + fileName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("》》》》》》》》》》加载路径出错");
            e.printStackTrace();
        }
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;// 图片的长宽都是原来的
        BufferedInputStream bis = new BufferedInputStream(f);
        bm = BitmapFactory.decodeStream(bis, null, options);
        if (bm != null) {
            previewImage.setImageBitmap(bm);
            previewImage.setVisibility(View.VISIBLE);
        }
    }
}
