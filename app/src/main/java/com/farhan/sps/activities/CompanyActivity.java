package com.farhan.sps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.farhan.sps.R;
import com.farhan.sps.fragments.AddCompanyFragment;
import com.farhan.sps.fragments.AddPastPaperFragment;
import com.farhan.sps.fragments.AddVacancyFragment;
import com.farhan.sps.fragments.ViewCompaniesFragment;
import com.farhan.sps.fragments.ViewPastPapersFragment;
import com.farhan.sps.fragments.ViewVacanciesFragment;

public class CompanyActivity extends AppCompatActivity {

    String companyId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView textView = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);

        String title = "";
        if (getIntent() != null) {
            title = getIntent().getStringExtra("title");

            if (getIntent().getStringExtra("companyId") != null) {
                companyId = getIntent().getStringExtra("companyId");
            }
        }

        textView.setText(title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (title.toLowerCase().contains("add company")) {
            Fragment addCompanyFragment = new AddCompanyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addCompanyFragment).addToBackStack(null).commit();
        } else if (title.toLowerCase().contains("add vacancy")) {
            Fragment addVacancyFragment = new AddVacancyFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addVacancyFragment).addToBackStack(null).commit();
        } else if (title.toLowerCase().contains("vacancies")) {
            Fragment vacanciesFragment = new ViewVacanciesFragment(companyId);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, vacanciesFragment).addToBackStack(null).commit();
        } else {
            Fragment viewCompaniesFragment = new ViewCompaniesFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, viewCompaniesFragment).addToBackStack(null).commit();

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