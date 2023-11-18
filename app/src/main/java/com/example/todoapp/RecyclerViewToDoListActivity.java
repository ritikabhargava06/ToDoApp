package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerViewToDoListActivity
        extends AppCompatActivity
        implements ToDoRecyclerAdapter.ToDoClickListener // step 3
{
    RecyclerView todoRecyclerView;
    ArrayList<ToDo> todoData;
    ToDoRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_to_do_list);
        todoData =  ((MyApp)getApplication()).listOfTodos; // data

        todoRecyclerView = findViewById(R.id.recyclerlist);
        adapter = new ToDoRecyclerAdapter(todoData,this);
        adapter.listener = this; // step 4
        todoRecyclerView.setAdapter(adapter);
       // todoRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // any click on the list item should be done in the adapter.
    }

    @Override
    public void onToDoClicked(int i) { // step 6
        // here we will get the notification of a todo clicking
        Toast.makeText(this,todoData.get(i).getTask(),Toast.LENGTH_LONG).show();
        // start another activity
        // update one todo
        // update the table

    }

    @Override
    public void onDoneClicked(int i) {
        todoData.get(i).setDone(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSwitchChecked(int i) {

        if(todoData.get(i).isUrgent()){
            todoData.get(i).setUrgent(false);
        }else{
            todoData.get(i).setUrgent(true);
        }
        adapter.notifyDataSetChanged();

    }
}