package me.varunon9.fakelock.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by varun on 26/11/17.
 */

public class HideAppsPreferenceUtility {

    private Context context;
    private SharedPreferences hideAppsPreference;
    private static final String HIDE_APPS_PREFERENCE_NAME = "hideAppsPreference";

    public HideAppsPreferenceUtility(Context context) {
        this.context = context;
        hideAppsPreference = context.getSharedPreferences(HIDE_APPS_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public void addAppToHiddenAppsList(String packageName) {
        if (!hideAppsPreference.contains(packageName)) {
            hideAppsPreference.edit().putString(packageName, packageName).apply();
        }
    }

    public void removeAppToHiddenAppsList(String packageName) {
        if (hideAppsPreference.contains(packageName)) {
            hideAppsPreference.edit().remove(packageName).apply();
        }
    }

    public boolean isAppHidden(String packageName) {
        return hideAppsPreference.contains(packageName);
    }

    public Map<String, ?> getAllHiddenAppsMap() {
        return hideAppsPreference.getAll();
    }
}