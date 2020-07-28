package com.affinityapps.endeavor.ui.master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.affinityapps.endeavor.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_FORM_REQUEST = 1;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpNavigation();
        //the idea behind this app is simple
        //do the same thing that's on google play but better
        //starting with code to put a nav view on bottom (I think that's what it's called)
        //maybe give it better material design as well to make it less dreary looking
        //one review said "I uninstalled. I need an all-in-one app which logs hours, miles, and purchases.
        //There doesn't seem to be an app that does it all. I did like that you can separate out different
        //projects you are working on. The picture option wasn't working as well. As an adult volunteer,
        //I need time, miles and purchases for tax purposes. If you can add in the missing pieces to make
        //a wrap around tracker for volunteering services, I'll happily reinstall.

        //The screens needed are one for filling out the forms
        //Most likely a home screen for displaying all the work that's been done
        //Probably another for statistics or something of the like
        //That's mostly it probably wouldn't hurt if I made a screen for settings or something
        //Also add another screen for seeing one's economic impact for the volunteer work they do
    }

    public void setUpNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_options, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_form_option:
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, ADD_FORM_REQUEST);
                return true;
            case R.id.dark_switch_option:
                //do switch
                return true;
            case R.id.grid_switch_option:
                //do more
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
