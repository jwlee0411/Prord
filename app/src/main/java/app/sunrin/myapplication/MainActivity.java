package app.sunrin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import app.sunrin.myapplication.ui.dashboard.DashboardFragment;
import app.sunrin.myapplication.ui.home.HomeFragment;
import app.sunrin.myapplication.ui.notifications.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println("＠@@@@@@@@");
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        navView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.app_bar_final_01));
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
                        break;

                    case R.id.navigation_dashboard:
                        navView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.app_bar_final_02));
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new NotificationsFragment()).commit();
                        break;

                    case R.id.navigation_notifications:
                        navView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.app_bar_final_03));
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new DashboardFragment()).commit();
                        break;



                }
                return true;
            }
        });



    }

}