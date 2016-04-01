package com.example.kevin.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private Button btn;
    // Adapter and ArrayList
    private ArrayAdapter<Assignment> assignmentArrayAdapter;
    private DataSource datasource;
    public static final String EXTRA_ASSIGNMENT = "extraAssignment";
    public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.task_list);

        TextView emptyView = (TextView) findViewById(R.id.main_list_empty);
        listView.setEmptyView(emptyView);

        //Initialize the views
        listView = (ListView) findViewById(R.id.task_list);

        datasource = new DataSource(this);
        List<Assignment> assignments = datasource.getAllAssignments();

        //Create the Array Adapter, give it a layout and a list of values
        assignmentArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);

        //Set the newly created adapter as the adapter for the listview
        listView.setAdapter(assignmentArrayAdapter);
        registerForContextMenu(listView);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this, CreateTask.class);
                    intent.putExtra(EXTRA_ASSIGNMENT_ID, assignmentArrayAdapter.getItem(position).getId());
                    startActivityForResult(intent, 2);
                }
            });

        btn = (Button) findViewById(R.id.taskView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateTask.class);
                startActivityForResult(intent, 1234);
            }
        });


        //Set the listview on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View listItem, int position, long id) {
                Intent intent = new Intent(MainActivity.this, HomeScreen.class);
               startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Delete") {
            Toast.makeText(getApplicationContext(), "Assignment deleted", Toast.LENGTH_LONG).show();
            Assignment assignment = assignmentArrayAdapter.getItem(info.position);
            assignmentArrayAdapter.remove(assignment);
            datasource.deleteAssignment(assignment);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //Handle data
                long assignmentId = data.getLongExtra(EXTRA_ASSIGNMENT_ID, -1);
                if (assignmentId != -1) {
                    Assignment assignment = datasource.getAssignment(assignmentId);
                    assignmentArrayAdapter.add(assignment);
                    updateAssignmentListView();

                }



            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                updateAssignmentListView();
            }
        }
    }
    public void updateAssignmentListView() {
        List<Assignment> assignments = datasource.getAllAssignments();
        assignmentArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);
        listView.setAdapter(assignmentArrayAdapter);
    }
}

