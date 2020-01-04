package com.makeinfo.smsalarm;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class PrefActivity extends PreferenceActivity {
    private static final String PREFERENCES_DESCRIPTION_DIALOG_SHOWN = "description_dialog_shown";

    public static final String PREFERENCES_ALARM_ENABLED = "alarm_enabled";

    public static final String PREFERENCES_ALARM_ACTIVATION_SMS = "alarm_activation_sms";

    public static final String PREFERENCES_ALARM_VIBRATE = "alarm_vibrate";
    public static final String PREFERENCES_ALARM_DURATION = "alarm_duration";

    public static final int ACTIVATION_REQUEST = 1;


    /*InterstitialAd mInterstitialAd;
    AdRequest adRequest = new AdRequest.Builder().build();
*/
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        /*mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1405561795242458/1204664928");
        mInterstitialAd.loadAd(adRequest);*/

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.getBoolean(PREFERENCES_DESCRIPTION_DIALOG_SHOWN, false)) {
            // TODO: HTML tutorial
        }

        Preference button = findPreference("test");
        Preference rate = findPreference("rate");
        Preference more = findPreference("more");

        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(PrefActivity.this,AlarmDialogActivity.class);
                startActivity(intent);
                return false;
            }
        });

        CheckBoxPreference  cb_vibrate = (CheckBoxPreference) findPreference("alarm_vibrate");
        cb_vibrate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startVibrate();
                return false;
            }
        });

        more.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("https://play.google.com/store/search?q=pub:MakeInfo" );
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=pub:MakeInfo")));
                }
                return false;
            }
        });

        rate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("market://details?id=com.makeinfo.smsalarm" );
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.makeinfo.smsalarm")));
                }
                return false;
            }
        });


    }
    Vibrator vibrator;
    private void startVibrate() {
        long pattern[] = {0,100};
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(300);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVATION_REQUEST:
                if (resultCode != Activity.RESULT_OK) {

                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
       /* if (mAdView != null) {
            mAdView.pause();
        }*/
        super.onPause();
    }


    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        /*if (mAdView != null) {
            mAdView.resume();
        }*/
    }


    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        /*if (mAdView != null) {
            mAdView.destroy();
        }*/
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
      /*  if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {

        }*/
        super.onBackPressed();

    }
}
