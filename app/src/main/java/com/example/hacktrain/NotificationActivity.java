package com.example.hacktrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView mRecucle_notificationa;
    private ArrayList<NotificationClass>notifications;
    private  NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initializeAllWighet();
        fetchData();

    }

    private void initializeAllWighet() {
        mRecucle_notificationa=findViewById(R.id.recucle_notificationa);
    }
    private void fetchData(){
        notifications=new ArrayList<>();
        notifications.add(new NotificationClass("New Request","HOOOO","Maanr Odeh"));
        adapter=new NotificationAdapter(notifications);
        mRecucle_notificationa.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        mRecucle_notificationa.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
