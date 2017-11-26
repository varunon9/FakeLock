package me.varunon9.fakelock.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by varun on 26/11/17.
 */

public class HideAppsPreferencesUtility {

    private Context context;
    private SharedPreferences hideAppsSharedPreferences;

    public int getHiddenAppsListLength() {
        return hideAppsSharedPreferences.getInt("hiddenAppsListLength", 0);
    }

    public void setHiddenAppsListLength(int hiddenAppsListLength) {
        hideAppsSharedPreferences.edit().putInt("hiddenAppsListLength", hiddenAppsListLength)
                .apply();
    }

    public HideAppsPreferencesUtility(Context context) {
        this.context = context;
        hideAppsSharedPreferences = context.getSharedPreferences("hideAppsPreferences",
                Context.MODE_PRIVATE);
    }

    public void addAppToHiddenAppsList(String packageName) {
        // todo
        setHiddenAppsListLength(getHiddenAppsListLength() + 1);
    }

    public void removeAppToHiddenAppsList(String packageName) {
        // todo
        setHiddenAppsListLength(getHiddenAppsListLength() - 1);
    }
}
