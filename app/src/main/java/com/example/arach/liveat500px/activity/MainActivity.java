package com.example.arach.liveat500px.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.arach.liveat500px.R;
import com.example.arach.liveat500px.dao.PhotoItemDao;
import com.example.arach.liveat500px.fragment.MainFragment;
import com.example.arach.liveat500px.fragment.MoreInfoFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle ;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();  //  ***MUST*** using findViewById() in a new method

        if(savedInstanceState == null){         // INITIAL fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }
    }

    /*   Add Hamburger Button  */
    private void initInstances() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this
                , drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*   Change Icon to Hamburger  */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /*  Selected Listener  */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPhotoItemClicked(PhotoItemDao dao) {

        FrameLayout frameLayout = findViewById(R.id.moreInfoContainer);

        if (frameLayout == null){
            Intent intent = new Intent(MainActivity.this,
                    MoreInfoActivity.class);
            intent.putExtra("dao", dao);
            startActivity(intent);
        }

        else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer, MoreInfoFragment.newInstance(dao))
                    .commit();
        }
    }
}
