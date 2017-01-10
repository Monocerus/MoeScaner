package com.moelover.moescaner;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.moelover.moescaner.activity.AboutApp;
import com.moelover.moescaner.broadcast.DownloadManagerReceiver;
import com.moelover.moescaner.fragment.RecycleImageFragment;
import com.moelover.moescaner.services.AutoDownloadService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int lastNavItemId = -1 ; //记录上次打开的fragmenttag
    DownloadManagerReceiver downloadManagerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadManagerReceiver = new DownloadManagerReceiver();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadManagerReceiver,intentFilter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            navigationView.setCheckedItem(R.id.nav_homepage);
            showNavigationFragement(R.id.nav_homepage);
        }
    }

    private void showNavigationFragement(int itemId) {
        //获取fragmentmanager
        FragmentManager fm = getFragmentManager();
        //获取fragmentTranslation
        FragmentTransaction ft = fm.beginTransaction();
        Fragment lastFragment = fm.findFragmentByTag(getTag(lastNavItemId)); //获取上一次的fragment
        lastNavItemId = itemId; //记录这次打开的fragmentTag
        if (lastFragment != null) {   //如果上次打开了一个fragment
            ft.detach(lastFragment);
        }
        Fragment fragment = fm.findFragmentByTag(getTag(itemId)); //获取这次的fragment
        if (fragment == null) {
            fragment = getItem(itemId);
            ft.add(R.id.container, fragment, getTag(itemId));
        } else {
            ft.attach(fragment);
        }
        ft.commit();
    }

    private Fragment getItem(int itemId) {
        Fragment fragment = null;
        switch (itemId){
            case R.id.nav_homepage :
                fragment = new RecycleImageFragment();
                break;
            case R.id.nav_localhost :
                fragment = new RecycleImageFragment();
                break;
            case R.id.nav_download :
                fragment = new RecycleImageFragment();
                break;
            case R.id.nav_manage :
                fragment = new RecycleImageFragment();
                break;
            case R.id.nav_share :
                fragment = new RecycleImageFragment();
                break;
            case R.id.nav_send :
        }
        return fragment;
    }

    private String getTag(int itemId) {
        return String.valueOf(itemId);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_download) {
            Intent intent = new Intent(this, AutoDownloadService.class);
            startService(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_send){
            Intent intent = new Intent(this, AboutApp.class);
            startActivity(intent);

        }else{
            showNavigationFragement(id);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(downloadManagerReceiver);
    }
}
