package com.gsmarena.firstsample.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.gsmarena.firstsample.R;
import com.gsmarena.firstsample.ui.content_home.ContentHomeFm;
import com.gsmarena.firstsample.ui.insert.CreateUserFragment;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FragmentManager manager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_insert, R.id.nav_delete, R.id.nav_update)
                .setDrawerLayout(drawer)
                .build();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.host_fragment, new ContentHomeFm(), ContentHomeFm.TAG).commitAllowingStateLoss();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_insert:
                manager.beginTransaction().replace(R.id.host_fragment, new CreateUserFragment(),
                        CreateUserFragment.TAG).addToBackStack(CreateUserFragment.TAG).commitAllowingStateLoss();
                drawer.close();
                return true;
            case R.id.nav_update:
                manager.beginTransaction().replace(R.id.host_fragment, new CreateUserFragment(),
                        CreateUserFragment.TAG).addToBackStack(CreateUserFragment.TAG).commitAllowingStateLoss();
                drawer.close();
                return true;
            case R.id.nav_delete:
                manager.beginTransaction().replace(R.id.host_fragment, new CreateUserFragment(),
                        CreateUserFragment.TAG).addToBackStack(CreateUserFragment.TAG).commitAllowingStateLoss();
                drawer.close();
                return true;
        }
        return false;
    }
}
