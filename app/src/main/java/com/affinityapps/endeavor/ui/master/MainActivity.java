package com.affinityapps.endeavor.ui.master;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.affinityapps.endeavor.R;
import com.affinityapps.endeavor.databinding.ActivityMainBinding;
import com.affinityapps.endeavor.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_DATE;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_HOURS;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ID;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_MILES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_ORGANIZATION;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PROJECT;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_PURCHASES;
import static com.affinityapps.endeavor.ui.home.HomeFragment.EXTRA_TITLE;

public class MainActivity extends AppCompatActivity implements HomeFragment.DataFragmentTransfer {

    public static final int ADD_FORM_REQUEST = 1;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.statisticsFragment, R.id.impactFragment,
                R.id.languageFragment, R.id.aboutFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        NavigationView navView = binding.navView;
        NavigationUI.setupWithNavController(navView, navController);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }
    @Override
    public void dataListInputSent(String id, String documentTitle, String organization,
                                  String project, String date, String hours, String miles, String purchases) {
        UpdateFormFragment fragment = new UpdateFormFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, id);
        bundle.putString(EXTRA_TITLE, documentTitle);
        bundle.putString(EXTRA_ORGANIZATION, organization);
        bundle.putString(EXTRA_PROJECT, project);
        bundle.putString(EXTRA_DATE, date);
        bundle.putString(EXTRA_HOURS, hours);
        bundle.putString(EXTRA_MILES, miles);
        bundle.putString(EXTRA_PURCHASES, purchases);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.item_detail_container, fragment).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
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
                Intent intent = new Intent(MainActivity.this, AddFormActivity.class);
                startActivityForResult(intent, ADD_FORM_REQUEST);
                return true;
            case R.id.take_screenshot_option:
                //code for screenshot
                return true;
            case R.id.dark_switch_option:
                int nightMode = AppCompatDelegate.getDefaultNightMode();
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode
                            (AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
