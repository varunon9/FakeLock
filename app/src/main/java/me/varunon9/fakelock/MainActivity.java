package me.varunon9.fakelock;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import me.varunon9.fakelock.help.HelpFragment;
import me.varunon9.fakelock.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment mainFragment = new MainFragment();
                    replaceFragment(mainFragment);
                    return true;
                case R.id.navigation_settings:
                    Fragment settingsFragment = new SettingsFragment();
                    replaceFragment(settingsFragment);
                    return true;
                case R.id.navigation_help:
                    Fragment helpFragment = new HelpFragment();
                    replaceFragment(helpFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this,LockScreenService.class));
        setContentView(R.layout.activity_main);

        Fragment mainFragment = new MainFragment();
        replaceFragment(mainFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(ApplicationInfo item) {

    }
}
