package com.example.hacktrain;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    ArrayList<NotificationClass>notificationClasses;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTxt_name_sender;
        public TextView mTxt_body;
        public CircleImageView iImg_circle_view;
        public ImageView mImg_delete;
        public ViewHolder(View viewItems) {
            super(viewItems);
              mTxt_name_sender=viewItems.findViewById(R.id.txt_name_sender);
              mTxt_body=viewItems.findViewById(R.id.txt_body);
              iImg_circle_view=viewItems.findViewById(R.id.img_circle_view);
              mImg_delete=viewItems.findViewById(R.id.img_delete);

        }
    }
    public NotificationAdapter(ArrayList<NotificationClass> Dataset) {
        notificationClasses = Dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_style,parent,false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView) ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        // Get the data model based on position
        NotificationClass contact = notificationClasses.get(position);


        ImageView img_bookmark=holder.iImg_circle_view;
        TextView mTxt_name_sender=holder.mTxt_name_sender;
        mTxt_name_sender.setText(contact.getmName_sender());
        TextView mTxt_body=holder.mTxt_body;
        mTxt_body.setText(contact.getmDescription());
        ImageView mImg_delete=holder.mImg_delete;
        Log.d("Wrong ",contact.getmDescription());
        mImg_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return notificationClasses.size();

    }
    public void removeItem(int position){
        notificationClasses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notificationClasses.size());
    }
}
