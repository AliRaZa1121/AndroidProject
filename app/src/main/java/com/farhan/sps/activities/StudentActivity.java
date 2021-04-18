package com.farhan.sps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.farhan.sps.R;
import com.farhan.sps.fragments.AddStudentFragment;
import com.farhan.sps.fragments.AddTPOFragment;
import com.farhan.sps.fragments.ViewStudentsFragment;
import com.farhan.sps.fragments.ViewTPOFragment;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);

        String title = "";
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");
        }

        textView.setText(title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (title.toLowerCase().contains("add")) {
            Fragment addStudentFragment = new AddStudentFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addStudentFragment).addToBackStack(null).commit();
        } else {
            Fragment viewStudentsFragment = new ViewStudentsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, viewStudentsFragment).addToBackStack(null).commit();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}