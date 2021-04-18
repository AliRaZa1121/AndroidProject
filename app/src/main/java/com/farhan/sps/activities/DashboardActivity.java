package com.farhan.sps.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.farhan.sps.BuildConfig;
import com.farhan.sps.R;
import com.farhan.sps.adapters.MainListAdapter;
import com.farhan.sps.models.MainItemModel;
import com.farhan.sps.models.UserModel;
import com.farhan.sps.utils.BaseActivity;
import com.farhan.sps.utils.SharedPrefrencesManager;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(_sGridLayoutManager);

        List<MainItemModel> sList = listData();

        MainListAdapter rcAdapter = new MainListAdapter(sList,
                DashboardActivity.this, new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MainItemModel item) {
                int id = item.getId();
                if (id == 1) {
                    Intent intent = new Intent(DashboardActivity.this, TPOActivity.class);
                    intent.putExtra("title", "Add TPO");
                    startActivity(intent);
                }

                if (id == 2) {
                    Intent intent = new Intent(DashboardActivity.this, TPOActivity.class);
                    intent.putExtra("title", "TPO's");
                    startActivity(intent);
                }

                if (id == 3) {
                    Intent intent = new Intent(DashboardActivity.this, StudentActivity.class);
                    intent.putExtra("title", "Add Student");
                    startActivity(intent);
                }

                if (id == 4) {
                    Intent intent = new Intent(DashboardActivity.this, StudentActivity.class);
                    intent.putExtra("title", "Students");
                    startActivity(intent);
                }

                if (id == 5) {
                    Intent intent = new Intent(DashboardActivity.this, CompanyActivity.class);
                    intent.putExtra("title", "Add Company");
                    startActivity(intent);
                }

                if (id == 12) {
                    Intent intent = new Intent(DashboardActivity.this, CompanyActivity.class);
                    intent.putExtra("title", "Add Vacancy");
                    startActivity(intent);
                }

                if (id == 6) {
                    Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
                    intent.putExtra("title", "Add Notification");
                    startActivity(intent);
                }

                if (id == 7) {
                    Intent intent = new Intent(DashboardActivity.this, PastPapersActivity.class);
                    intent.putExtra("title", "Add Previous Paper");
                    startActivity(intent);
                }

                if (id == 8) {
                    Toast.makeText(DashboardActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }


                if (id == 9) {
                    Intent intent = new Intent(DashboardActivity.this, CompanyActivity.class);
                    intent.putExtra("title", "View Companies");
                    startActivity(intent);
                }

                if (id == 10) {
                    Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
                    intent.putExtra("title", "View Notifications");
                    startActivity(intent);
                }

                if (id == 11) {
                    Intent intent = new Intent(DashboardActivity.this, PastPapersActivity.class);
                    intent.putExtra("title", "View Previous Paper");
                    startActivity(intent);
                }


            }
        });
        recyclerView.setAdapter(rcAdapter);
        runLayoutAnimation(recyclerView);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private List<MainItemModel> listData() {
        UserModel.User user = SharedPrefrencesManager.getInstance(this).customer();
        List<MainItemModel> list = new ArrayList<>();

        if (user.getUserTypeId().equals("1")) {
            list.add(new MainItemModel(1, "Add TPO", R.drawable.add_tpo, "#003f5c"));
            list.add(new MainItemModel(2, "View TPO's", R.drawable.view_tpo, "#2f4b7c"));
            list.add(new MainItemModel(3, "Add Students", R.drawable.add_student, "#940288D1"));
            list.add(new MainItemModel(4, "View Students", R.drawable.view_students, "#88A8D5"));
        }
        if (user.getUserTypeId().equals("2")) {
            list.add(new MainItemModel(5, "Add Company", R.drawable.add_company, "#003f5c"));
            list.add(new MainItemModel(12, "Add Vacancy", R.drawable.add_company, "#003f5c"));
            list.add(new MainItemModel(6, "Send Notifications", R.drawable.notifications_ico, "#2f4b7c"));
            list.add(new MainItemModel(7, "Add Previous Papers", R.drawable.past_papers_ico, "#940288D1"));
            list.add(new MainItemModel(8, "Add Selected Students", R.drawable.list_icon, "#88A8D5"));
        }
        if (user.getUserTypeId().equals("3")) {
            list.add(new MainItemModel(9, "View Companies", R.drawable.add_company, "#003f5c"));
            list.add(new MainItemModel(10, "View Notifications", R.drawable.notifications_ico, "#2f4b7c"));
            list.add(new MainItemModel(11, "View Previous Papers", R.drawable.past_papers_ico, "#940288D1"));
        }


        return list;
    }

    private void runLayoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);
        recyclerView.setLayoutAnimation(controller);
        if (recyclerView.getAdapter() != null)
            recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_privacy) {

            Toast.makeText(this, "Privacy", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.nav_terms) {
            Toast.makeText(this, "Terms", Toast.LENGTH_SHORT).show();

        }

        if (item.getItemId() == R.id.nav_contact_us) {

            int apiVersion = Build.VERSION.SDK_INT;
            String appVersion = BuildConfig.VERSION_NAME;
            String device = Build.MANUFACTURER + " - " + Build.MODEL;
            String body = "\n\n\n---------------------" +
                    "\nPlease do not write below this line" +
                    "\nApp version : " + appVersion +
                    "\nDevice Api: " + apiVersion +
                    "\nDevice: " + device;
            String[] emails = {"support@aptech.com"};
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, emails);
            intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.desc));
            intent.putExtra(Intent.EXTRA_TEXT, body);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        if (item.getItemId() == R.id.nav_share_app) {
            final Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String appFullName = getString(R.string.app_name) + " - " + getString(R.string.desc);
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, appFullName);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
            startActivity(Intent.createChooser(shareIntent, "Share"));

        }

        if (item.getItemId() == R.id.nav_logout) {
            SharedPrefrencesManager.getInstance(this).logout();
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(DashboardActivity.this, LoginActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();


        }


        return false;
    }


}