package com.udacity.gradle.builditbigger;

import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/19
 * Email: vhly@163.com
 */
public class FreeAdSupport implements AdSupport {

    @Override
    public void initAdView(View rootView) {
        com.google.android.gms.ads.AdView mAdView = (com.google.android.gms.ads.AdView) rootView.findViewById(R.id.adView);
        if (mAdView != null) {
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
                    .addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
        }

    }
}
