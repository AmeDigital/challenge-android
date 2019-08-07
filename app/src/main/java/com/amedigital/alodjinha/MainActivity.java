package com.amedigital.alodjinha;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.amedigital.alodjinha.fragment.HomeFragment;
import com.amedigital.alodjinha.fragment.SobreFragment;
import com.amedigital.alodjinha.interfaces.ComunicadorMainActvityInterface;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ComunicadorMainActvityInterface {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        inicializar();
        abrirFragment(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fM = getSupportFragmentManager();
            if (fM.getBackStackEntryCount() > 0) {
                fM.popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuHome:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case R.id.mnuSobre:
                abrirFragment(false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void inicializar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
    }

    private void abrirFragment(boolean home){
        Fragment fragment;
        FragmentManager fM = getSupportFragmentManager();
        if (home) {
            fragment = HomeFragment.newInstance();
            fM.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fM.beginTransaction().replace(R.id.frameLayout, fragment, "home").commit();
        }else{
            fragment = SobreFragment.newInstance();
            fM.beginTransaction().replace(R.id.frameLayout, fragment, "sobre").addToBackStack(null).commit();
        }
    }

    @Override
    public void setMenu(Toolbar toolbar) {
        navigationView.setItemIconTintList(null);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}