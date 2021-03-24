package com.aprendiendoando.navigationdrawer;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentGaleria.OnFragmentInteractionListener,
        FragmentSlides.OnFragmentInteractionListener,
        FragmentPrincipal.OnFragmentInteractionListener{

    DrawerLayout drawer;
    FloatingActionButton fab;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    Fragment fragmenprincipal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Se crea por defecto el Fragment, hay que crear la instancia

        fragmenprincipal = new FragmentPrincipal();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main, fragmenprincipal).commit();



        // Se crea las acciones del Drawer
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Aca trae el menu, que se "infla" con XML del menu que se creo
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Opciones del Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Se crea una variable de fragmento para asignar despues la vista
        // y el boolean para saber si hay un cambio

        Fragment mifragmento = null;
        boolean fragmentseleccionado = false;

        switch (item.getItemId())
        {
            case  R.id.nav_camera:
                mifragmento = new FragmentPrincipal();
                fragmentseleccionado = true;
                break;

            case  R.id.nav_gallery:
                mifragmento = new FragmentGaleria();
                fragmentseleccionado = true;
                break;

            case  R.id.nav_slideshow:
                mifragmento = new FragmentSlides();
                fragmentseleccionado = true;
                break;

            case  R.id.nav_manage:
                mifragmento = new FragmentGaleria();
                fragmentseleccionado = true;
                break;

            case  R.id.nav_share:
                mifragmento = new FragmentGaleria();
                fragmentseleccionado = true;
                break;

            case  R.id.nav_send:
                mifragmento = new FragmentGaleria();
                fragmentseleccionado = true;
                break;
        }

        if (fragmentseleccionado)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, mifragmento).commit();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
