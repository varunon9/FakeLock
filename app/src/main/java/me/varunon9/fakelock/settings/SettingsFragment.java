package me.varunon9.fakelock.settings;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.annotation.Nullable;

import me.varunon9.fakelock.R;

public class SettingsFragment extends PreferenceFragmentCompat {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.settings_preferences);
    }

}
