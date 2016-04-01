package com.example.kevin.androidapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CreateTask extends AppCompatActivity {
    private MainActivity activity;
    private Button btn;
    private EditText taskName, taskDescription;
    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Handle the button click

        btn = (Button) findViewById(R.id.createButton);
        taskName = (EditText)this.findViewById(R.id.taskName);
        taskDescription = (EditText)this.findViewById(R.id.taskDescription);
        datasource = new DataSource(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long assignmentId = datasource.createAssignment(taskDescription.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);

                //Send the result back to the activity
                setResult(Activity.RESULT_OK, resultIntent);
                activity.updateAssignmentListView();
                //Finish this activity
                finish();
            }


        });

    }
}
