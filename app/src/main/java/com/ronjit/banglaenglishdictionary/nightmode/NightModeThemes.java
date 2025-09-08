package com.ronjit.banglaenglishdictionary.nightmode;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatDelegate;

import com.ronjit.banglaenglishdictionary.R;

public class NightModeThemes {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    // Constructor: SharedPreferences setup
    public NightModeThemes(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Load previously saved night mode state
    public void applyNightMode() {
        boolean isNightMode = sharedPreferences.getBoolean("nightMode", false);
        AppCompatDelegate.setDefaultNightMode(
                isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    // Toggle night mode and recreate activity smoothly
    public void toggleNightMode(Context context) {
        boolean isNightMode = sharedPreferences.getBoolean("nightMode", false);
        editor.putBoolean("nightMode", !isNightMode);
        editor.apply();

        AppCompatDelegate.setDefaultNightMode(
                isNightMode ? AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES
        );

        if (context instanceof Activity) {
            Activity activity = (Activity) context;

            // Immediately recreate without delay, no animation
            activity.overridePendingTransition(0, 0); // before recreate
            activity.recreate();
            activity.overridePendingTransition(0, 0); // after recreate
        }
    }

}
