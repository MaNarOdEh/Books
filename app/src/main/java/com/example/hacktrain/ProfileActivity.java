package com.example.hacktrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ProfileActivity extends AppCompatActivity {
    RelativeLayout mFollow,mFolloers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mFollow=findViewById(R.id.follow);
        mFolloers=findViewById(R.id.folloers);
        mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotFollowActvitity();
            }

        });
        mFolloers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotFollowActvitity();

            }
        });
    }
    private void gotFollowActvitity(){
        startActivity(new Intent(ProfileActivity.this,FolowersActivity.class));
    }
}
