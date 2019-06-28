package com.example.hacktrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchForABook extends AppCompatActivity {


    private static final int RESULT_IMAGE_CAPTURE = 22;
    private  Dialog dialog_confirm;
    private AutoCompleteTextView mAuto_complete_book_name;
    private ImageView mImg_search_result,mImg_camera;
    private Button mBtn_search,mBtn_confirm_upload,mBtn_cancel_confirm;
    private CardView mCard_result;
    private CircleImageView mImg_book;
    private TextView mTxt_book_name;
    private  TextView mTxt_book_auth;
    private  ImageView mImg_star1,mImg_star2,mImg_star3,mImg_star4,mImg_star5,mImg_capture_camera,mImg_show_result;
    private  Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_abook);
        initlializeAllWighet();
        makeEvent();

    }
    private void initlializeAllWighet() {
        mAuto_complete_book_name=findViewById(R.id.auto_complete_book_name);
        mImg_search_result=findViewById(R.id.img_search_result);
        mImg_camera=findViewById(R.id.img_camera);
        mBtn_search=findViewById(R.id.btn_search);
        mCard_result=findViewById(R.id.card_result);
        mImg_book=findViewById(R.id.img_book);
        mTxt_book_name=findViewById(R.id.txt_book_name);
        mTxt_book_auth=findViewById(R.id.txt_book_auth);
        mImg_star1=findViewById(R.id.img_star1);
        mImg_star2=findViewById(R.id.img_star2);
        mImg_star3=findViewById(R.id.img_star3);
        mImg_star4=findViewById(R.id.img_star4);
        mImg_star5=findViewById(R.id.img_star5);
        mImg_capture_camera=findViewById(R.id.img_capture_camera);
        dialog_confirm= new Dialog(SearchForABook.this);
        dialog_confirm.setCancelable(false);
        dialog_confirm.setContentView(R.layout.confirm_upload);
        mImg_show_result=dialog_confirm.findViewById(R.id.img_show_result);
        mBtn_confirm_upload=dialog_confirm.findViewById(R.id.btn_confirm_upload);
        mBtn_cancel_confirm=dialog_confirm.findViewById(R.id.btn_cancel_confirm);
    }
    private void makeEvent() {
        mImg_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ContextCompat.checkSelfPermission( SearchForABook.this, android.Manifest.permission.ACCESS_COARSE_LOCATION )
                        != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(SearchForABook.this, new String[]{Manifest.permission.CAMERA},1);
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);

                }else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);
                }

            }
        });
        mBtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImg_search_result.setVisibility(View.GONE);
                mCard_result.setVisibility(View.VISIBLE);


            }
        });
        mBtn_confirm_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable d=new BitmapDrawable(getApplication().getResources(),bitmap);
                mImg_capture_camera.setImageBitmap(bitmap);
                mImg_capture_camera.setVisibility(View.VISIBLE);
                dialog_confirm.dismiss();
            }
        });
        mBtn_cancel_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=null;
                dialog_confirm.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== RESULT_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            if(bitmap!=null){
                dialog_confirm.show();
                if(bitmap!=null) {
                    mImg_show_result.setImageBitmap(bitmap);
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog_confirm.isShowing()){
            dialog_confirm.dismiss();
        }
    }


}
