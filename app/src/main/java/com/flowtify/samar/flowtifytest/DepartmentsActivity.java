package com.flowtify.samar.flowtifytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DepartmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        Bundle arguments = getIntent().getExtras();
        DepartmentsFragments fragment = new DepartmentsFragments();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.Departments_container, fragment)
                .commit();
    }

}
