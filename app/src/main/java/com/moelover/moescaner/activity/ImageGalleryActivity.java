package com.moelover.moescaner.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.moelover.moescaner.ApplicationController;
import com.moelover.moescaner.R;
import com.moelover.moescaner.model.ImageModelYande;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageGalleryActivity extends AppCompatActivity {

    ArrayList<ImageModelYande> list;
    int position ;
    ImageView imageView;
    ImageView btnSaveFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        imageView = (ImageView) findViewById(R.id.iv_imageGallery);
        btnSaveFile = (ImageView)findViewById(R.id.iv_save_file);
        Intent intent = getIntent();
        if(intent!=null){
             Bitmap bitmap = intent.getParcelableExtra("image");
            list = (ArrayList<ImageModelYande>) intent.getSerializableExtra("listImage");
            position = intent.getIntExtra("position", -1);
            imageView.setImageBitmap(bitmap);
            btnSaveFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageRequest imageRequest = new ImageRequest(
                            list.get(position).getSample_url(),
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    Log.d("tianlele", "成功获取数据");
                                    saveBitmap(response, getFileName(list.get(position).getFile_url()));
                                    Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
                                }
                            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("tianlele","下载出错");
                        }
                    }){
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String,String> headMap = new HashMap<String,String>();
                            headMap.put("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
                            return headMap;
                        }
                    };
                    ApplicationController.getInstance().addToRequestQueue(imageRequest);
                }
            });
        }

    }

    /** 保存方法 */
    public void saveBitmap(Bitmap bm ,String picName) {
        File f = new File("/sdcard/moelover/", picName);
        if (f.exists()) {
           // f.delete();
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("tianlele", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName(String url){
       String results[] = url.split("/");

        String restult = results[results.length-1];

        return restult;
    }
}
