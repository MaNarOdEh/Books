package com.example.hacktrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostABook extends AppCompatActivity {
    private static final int RESULT_IMAGE_CAPTURE = 22;
    private EditText mEdit_situations,mEdit_money;
    private Button  mBtn_upload_img,mBtn_post,mBtn_capture_img,mBtn_confirm_upload,mBtn_cancel_confirm;;
    private RecyclerView mPost_recycle_view;
    private  Bitmap bitmap;
    private Dialog mDialog_confirm;
    private ImageView mImg_show_result;
    private static  final int RESULT_UPLOPAD_FROM_GALLERY=44;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_abook);
        initializeAllWighet();
        makeEvents();
    }

    private void initializeAllWighet() {
        mEdit_situations=findViewById(R.id.edit_situations);
        mEdit_money=findViewById(R.id.edit_money);
        mBtn_upload_img=findViewById(R.id.btn_upload_img);
        mBtn_post=findViewById(R.id.btn_post);
        mBtn_capture_img=findViewById(R.id.btn_capture_img);
        mPost_recycle_view=findViewById(R.id.post_recycle_view);
        mDialog_confirm = new Dialog(PostABook.this);
        mDialog_confirm.setCancelable(false);
        mDialog_confirm.setContentView(R.layout.confirm_upload);
        mImg_show_result= mDialog_confirm.findViewById(R.id.img_show_result);
        mBtn_confirm_upload= mDialog_confirm.findViewById(R.id.btn_confirm_upload);
        mBtn_cancel_confirm= mDialog_confirm.findViewById(R.id.btn_cancel_confirm);
    }
    private void makeEvents(){
        mBtn_upload_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), RESULT_UPLOPAD_FROM_GALLERY);


            }
        });
        mBtn_capture_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ContextCompat.checkSelfPermission( PostABook.this, android.Manifest.permission.ACCESS_COARSE_LOCATION )
                        != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(PostABook.this, new String[]{Manifest.permission.CAMERA},1);
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);

                }else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);
                }
            }
        });
        mBtn_capture_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtn_confirm_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable d=new BitmapDrawable(getApplication().getResources(),bitmap);
                mDialog_confirm.dismiss();
            }
        });
        mBtn_cancel_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=null;
                mDialog_confirm.dismiss();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==RESULT_UPLOPAD_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            bitmap = null;
            try {

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                mDialog_confirm.show();
                if(bitmap!=null) {
                    mImg_show_result.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(requestCode== RESULT_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            if(bitmap!=null){
                mDialog_confirm.show();
                if(bitmap!=null) {
                    mImg_show_result.setImageBitmap(bitmap);
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDialog_confirm.isShowing()){
            mDialog_confirm.dismiss();
        }
    }
}
