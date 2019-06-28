package com.example.hacktrain;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<PostClass >postData;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTxt_name;
        public TextView mTxt_date;
        public  TextView mTxt_situation;
        public CircleImageView iImg_circle_view;
        public ImageView mBtn_del,mBook_img;
        public Button mBtn_request,mBtn_call_us;
        public ViewHolder(View viewItems) {
            super(viewItems);
            mTxt_name=viewItems.findViewById(R.id.txt_name);
            mTxt_date=viewItems.findViewById(R.id.txt_date);
            mTxt_situation=viewItems.findViewById(R.id.txt_situation);
            mBtn_del=viewItems.findViewById(R.id.btn_del);
            mBook_img=viewItems.findViewById(R.id.book_img);
            mBtn_call_us=viewItems.findViewById(R.id.btn_call_us);
            mBtn_request=viewItems.findViewById(R.id.btn_request);


        }
    }
    public PostAdapter(ArrayList<PostClass> Dataset) {
        postData = Dataset;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView= LayoutInflater.from(parent.getContext()).inflate(R.layout.post,parent,false);
        // Return a new holder instance
        PostAdapter.ViewHolder viewHolder = new PostAdapter.ViewHolder(contactView) ;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int  position) {
        PostClass contact = postData.get(position);


        TextView mTxt_name_sender=holder.mTxt_name;
        mTxt_name_sender.setText(contact.getmPost_name());

        TextView mTxt_body=holder.mTxt_date;
        mTxt_body.setText(contact.getmDate());

        TextView mTxt_situation=holder.mTxt_situation;
        mTxt_situation.setText(contact.getmPost_description());

        ImageView mBook_img=holder.mBook_img;
        mBook_img.setImageBitmap(contact.getmBitmap());

        ImageView mImg_delete=holder.mBtn_del;

        Button mBtn_request=holder.mBtn_request;
        Button mBtn_call_us=holder.mBtn_call_us;

        mBtn_call_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImg_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });


    }
    @Override
    public int getItemCount() {
        return postData.size();

    }
    public void removeItem(int position){
        postData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, postData.size());
    }

}
