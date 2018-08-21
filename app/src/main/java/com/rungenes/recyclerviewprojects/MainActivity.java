package com.rungenes.recyclerviewprojects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
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
        setButtons();


    }
    public void insertItem(int position){

        mExampleList.add(position,new ExampleItem(R.drawable.ic_android,"New text at Position"+position,"Line 2"));
        mAdapter.notifyItemInserted(position);

    }

    public void removeItem(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        


    }

    public void changeItem(int position,String text){
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);

    }

    public void changeItem2(int position,String text){
        mExampleList.get(position).changeText2(text);
        mAdapter.notifyItemChanged(position);

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
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Toast.makeText(MainActivity.this, "Item "+position+"Clicked", Toast.LENGTH_SHORT).show();

                changeItem(position,"clicked");
                changeItem2(position,"clicked2");

            }


            @Override
            public void onItemDelete(int position) {

                removeItem(position);
                Toast.makeText(MainActivity.this, "Item "+position+"Deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                ExampleItem currentItem = mExampleList.get(position);

                mExampleList.get(position).changeText1("Line1");

                Toast.makeText(MainActivity.this, "Item "+position+"Long clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void setButtons(){

        edittextInsert = findViewById(R.id.edittext_insert);
        edittextRemove = findViewById(R.id.edittext_remove);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUserName = edittextInsert.getText().toString();
                if(TextUtils.isEmpty(strUserName)) {
                    edittextInsert.setError("Can not be empty");
                    return;
                }

                int position = Integer.parseInt(edittextInsert.getText().toString());

                insertItem(position);

            }
        });
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUserName = edittextRemove.getText().toString();
                if(TextUtils.isEmpty(strUserName)) {
                    edittextInsert.setError("Can not be empty");
                    return;
                }
                int position = Integer.parseInt(edittextRemove.getText().toString());
                removeItem(position);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
