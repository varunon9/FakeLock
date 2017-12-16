package me.varunon9.fakelock.settings;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import me.varunon9.fakelock.R;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends PreferenceFragmentCompat {

    private int PICK_IMAGE_REQUEST = 1;
    private SharedPreferences settingsPreferences;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        settingsPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.settings_preferences);

        Preference backgroundPreference = (Preference) findPreference(SettingsPreferencesKeys.
                BACKGROUND_PREFERENCE_KEY);
        backgroundPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        PICK_IMAGE_REQUEST);
                return true;
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && imageReturnedIntent != null) {
            Uri selectedImage = imageReturnedIntent.getData();
            if (selectedImage != null) {
                settingsPreferences.edit().putString(SettingsPreferencesKeys.
                        BACKGROUND_PREFERENCE_KEY, selectedImage.toString()).apply();
            }
        }
    }

}
