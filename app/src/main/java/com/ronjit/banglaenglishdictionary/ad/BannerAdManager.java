package com.ronjit.banglaenglishdictionary.ad;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class BannerAdManager {
    private Context context;
    private LinearLayout adLayout;
    private String adUnitId;
    private AdView adView;
    private int clickCount = 0;
    private static final int MAX_CLICKS = 2;
    private static final long HIDE_DURATION_MS = 1 * 60 * 60 * 1000; // 8 hours

    private SharedPreferences sharedPreferences;
    private String prefName;
    private String keyHideTime;

    public BannerAdManager(Context context, LinearLayout adLayout, String adUnitId, String prefName) {
        this.context = context;
        this.adLayout = adLayout;
        this.adUnitId = adUnitId;
        this.prefName = prefName;
        this.keyHideTime = "hide_time_" + prefName; // unique key per fragment
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public void loadBannerAd() {
        long hideUntil = sharedPreferences.getLong(keyHideTime, 0);
        long currentTime = System.currentTimeMillis();

        if (currentTime < hideUntil) {
            adLayout.setVisibility(View.GONE);
            return;
        }

        adView = new AdView(context);
        adView.setAdUnitId(adUnitId);
        adView.setAdSize(getAdSize());

        adLayout.removeAllViews();
        adLayout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                clickCount++;
                if (clickCount >= MAX_CLICKS) {
                    hideBannerForDuration();
                }
            }
        });
    }

    private AdSize getAdSize() {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    private void hideBannerForDuration() {
        adLayout.setVisibility(View.GONE);
        long hideUntil = System.currentTimeMillis() + HIDE_DURATION_MS;
        sharedPreferences.edit().putLong(keyHideTime, hideUntil).apply();

        clickCount = 0;

        Toast.makeText(context, "Ad hidden for 8 hours", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(this::loadBannerAd, HIDE_DURATION_MS);
    }

    //=========================================================
    // Lifecycle methods to be called from Activity/Fragment
    public void resumeAd() {
        if (adView != null) {
            adView.resume();
        }
    }

    public void pauseAd() {
        if (adView != null) {
            adView.pause();
        }
    }

    public void destroyAd() {
        if (adView != null) {
            adView.destroy();
            adView = null;
        }
    }
}
