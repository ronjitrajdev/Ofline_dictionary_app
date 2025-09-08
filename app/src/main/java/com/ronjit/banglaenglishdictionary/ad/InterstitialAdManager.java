package com.ronjit.banglaenglishdictionary.ad;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InterstitialAdManager {

    private static final String TAG = "InterstitialAdManager";
    private static final String PREFS_NAME = "interstitial_prefs";

    private static final long MIN_INTERVAL_MS =  3 * 60 * 1000;           // 3 min between ads
    private static final int MAX_DAILY_ADS = 3;                      // max 3 ads per day per tag

    private final Context context;
    private final String adUnitId;
    private InterstitialAd interstitialAd;

    public InterstitialAdManager(Context context, String adUnitId) {
        this.context = context.getApplicationContext();
        this.adUnitId = adUnitId;
        loadAd();
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                interstitialAd = ad;
                Log.d(TAG, "Ad loaded.");

                ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        interstitialAd = null;
                        loadAd(); // preload next
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        interstitialAd = null;
                        Log.e(TAG, "Ad failed to show: " + adError.getMessage());
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError error) {
                interstitialAd = null;
                Log.e(TAG, "Ad failed to load: " + error.getMessage());
            }
        });
    }

    public void showAdIfAvailable(Activity activity, String tag) {
        if (interstitialAd != null && canShowAd(tag)) {
            interstitialAd.show(activity);
            updateAdData(tag);
        } else {
            Log.d(TAG, "Ad not ready or not allowed yet.");
            loadAd(); // preload again
        }
    }

    private boolean canShowAd(String tag) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        long lastShown = prefs.getLong(tag + "_last_shown", 0);
        String today = new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date());
        int todayCount = prefs.getInt(tag + "_count_" + today, 0);
        long now = System.currentTimeMillis();

        // Limit: max 3 times/day & 1 min gap
        if (todayCount >= MAX_DAILY_ADS) {
            Log.d(TAG, "Ad limit reached today for tag: " + tag);
            return false;
        }
        if ((now - lastShown) < MIN_INTERVAL_MS) {
            Log.d(TAG, "Ad interval too short for tag: " + tag);
            return false;
        }

        return true;
    }

    private void updateAdData(String tag) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String today = new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date());

        int todayCount = prefs.getInt(tag + "_count_" + today, 0);
        editor.putInt(tag + "_count_" + today, todayCount + 1);
        editor.putLong(tag + "_last_shown", System.currentTimeMillis());
        editor.apply();

        Log.d(TAG, "Ad shown for tag: " + tag + " | Count today: " + (todayCount + 1));
    }
}
