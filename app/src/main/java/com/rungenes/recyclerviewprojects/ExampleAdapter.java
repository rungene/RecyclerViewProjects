package com.rungenes.recyclerviewprojects;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.MyViewHolder> implements Filterable{

    private ArrayList<ExampleItem> mExampleList;
    private   OnItemClickListener mListener;
    private ArrayList<ExampleItem> mExampleListFull;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemDelete(int position);
        void onItemLongClick(int position);


    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2;
        ImageView imageView, mDeleteImage;



        public MyViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            mDeleteImage = itemView.findViewById(R.id.image_delete);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);

                        }

                    }


                }
            });
            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!= null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemDelete(position);
                        }

                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemLongClick(position);

                        }
                    }
                    return true;
                }
            });

        }

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);

        MyViewHolder mvh = new MyViewHolder(v,mListener);
        return mvh;


    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
        mExampleListFull = new ArrayList<>(exampleList);

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


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ExampleItem> filteredList = new ArrayList<>();
            if (charSequence==null || charSequence.length() ==0){
                filteredList.addAll(mExampleListFull);

            }else {
                String filetredPattern = charSequence.toString().toLowerCase().trim();

                for (ExampleItem item:mExampleListFull) {
                    if (item.getmText1().toLowerCase().contains(filetredPattern)){
                        filteredList.add(item);
                    }


                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List)(results.values));
            notifyDataSetChanged();


        }
    };
}
