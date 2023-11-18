package com.example.todoapp;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.activity.result.ActivityResultContracts;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    FloatingActionButton addToDoButton;
    FloatingActionButton toRecyclerListButton;

    ActivityResultLauncher<Intent> addTodoActivityResultLuncher;
    ArrayList<ToDo> todoData;
    ToDoBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.todolist); // Adapter View
        todoData =  ((MyApp)getApplication()).listOfTodos; // data

        adapter = new ToDoBaseAdapter(todoData,this);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,todoData.get(i).getTask(),Toast.LENGTH_LONG).show();
            }
        });
        list.setAdapter(adapter);
        addTodoActivityResultLuncher = registerForActivityResult(
               new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActivityResult activity) {
                        // get the extra which is the ToDo
                        // insert the new todo to the array list
                        if (activity.getResultCode() == RESULT_OK) {
                            ToDo todo = (ToDo) activity.getData().getSerializableExtra("newtodo");
                            todoData.add(todo);
                           // adapter = new ToDoBaseAdapter(todoData,MainActivity.this);
                           // list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
                );


        addToDoButton = findViewById(R.id.addNewToDo);
        addToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start add new todo activity for result.
                // the result is the ToDo Object.
                Intent addNewTodoIntent = new Intent(MainActivity.this,AddToDoAvtivity.class);
                addTodoActivityResultLuncher.launch(addNewTodoIntent);
            }
        });
        toRecyclerListButton = findViewById(R.id.torecyclerlist);
        toRecyclerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recyclerListIntent = new Intent(MainActivity.this,RecyclerViewToDoListActivity.class);
                startActivity(recyclerListIntent);
            }
        });

    }
}