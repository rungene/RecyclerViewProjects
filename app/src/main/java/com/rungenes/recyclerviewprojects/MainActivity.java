package com.rungenes.recyclerviewprojects;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_android,"Line1","Line2"));
        exampleList.add(new ExampleItem(R.drawable.ic_audiotrack,"Line3","Line4"));
        exampleList.add(new ExampleItem(R.drawable.ic_wb_sunny,"Line5","Line6"));
    }


}
