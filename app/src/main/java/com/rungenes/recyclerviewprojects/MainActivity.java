package com.rungenes.recyclerviewprojects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> mExampleList;
    private EditText edittextInsert,edittextRemove;
    private Button  buttonInsert,buttonRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createExampleList();
        buiildRecyclerView();

        edittextInsert = findViewById(R.id.edittext_insert);
        edittextRemove = findViewById(R.id.edittext_remove);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(edittextInsert.getText().toString());
                insertItem(position);



            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = Integer.parseInt(edittextRemove.getText().toString());
                removeItem(position);


            }
        });


    }
    public void insertItem(int position){

        mExampleList.add(position,new ExampleItem(R.drawable.ic_android,"New text at Position"+position,"Line 2"));
        mAdapter.notifyItemInserted(position);

    }

    public void removeItem(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        


    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line1","Line2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_audiotrack,"Line3","Line4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_wb_sunny,"Line5","Line6"));


    }

    private void buiildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


}
