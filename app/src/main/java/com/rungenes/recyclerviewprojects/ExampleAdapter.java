package com.rungenes.recyclerviewprojects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.MyViewHolder> {

    private ArrayList<ExampleItem> mExampleList;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);

        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;



    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        ExampleItem currentItem = mExampleList.get(position);

        myViewHolder.imageView.setImageResource(currentItem.getmImageRescource());
        myViewHolder.textView1.setText(currentItem.getmText1());
        myViewHolder.textView2.setText(currentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;
        ImageView imageView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
