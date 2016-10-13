package com.ingsoft.url.gudbox;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int HOME_FRAME = 0;
    private int SEEDS_FRAME = 1;
    private int FARMING_FRAME = 2;

    private String tilesNames[] = {
            "Temperature",
            "Humidity",
            "Brightness",
            "Seeds"
    };

    private Integer tilesColors[] = {
            R.color.paletteAmber,
            R.color.paletteBlue,
            R.color.paletteTeal,
            R.color.paletteRed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ecosystem");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(HOME_FRAME);
        /* uncomment this to try API connection */
        //_API_Connection api =  new _API_Connection( "api/GetSeeds");
        //api.execute
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_sensors:
                setFragment(HOME_FRAME);
            break;
            case R.id.nav_all:
                setFragment(SEEDS_FRAME);
                break;
            case R.id.nav_farm:
                setFragment(FARMING_FRAME);
                break;
            case R.id.nav_fruits:
                int bla = 0;
                bla++;
                InternalAPI api = new InternalAPI();
                api.downloadSeeds();
                List<ServerSeed> serverSeeds = api.getAllSeeds();
                bla++;
                break;
            default:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        switch (position) {
            case 0: //MENU_FRAME
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.frameContent, homeFragment);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Ecosystem");
                break;

            case 1: //SEEDS_FRAME
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                SeedsListFragment seedsListFragment = new SeedsListFragment();
                fragmentTransaction.replace(R.id.frameContent, seedsListFragment);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("All seeds");
                break;

            case 2: //FARMING FRAME
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FarmingListFragment farmingListFragment = new FarmingListFragment();
                fragmentTransaction.replace(R.id.frameContent, farmingListFragment);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("Farming");
            break;

            default:
                getSupportActionBar().setTitle("Gudbox");
                break;
        }

    }
}
