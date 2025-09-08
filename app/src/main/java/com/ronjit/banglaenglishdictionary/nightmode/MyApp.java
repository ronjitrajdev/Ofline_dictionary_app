package com.ronjit.banglaenglishdictionary.nightmode;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Dark Mode Apply – এখান থেকে করলেই crash হবে না
        NightModeThemes nightModeThemes = new NightModeThemes(this);
        nightModeThemes.applyNightMode();
    }
}