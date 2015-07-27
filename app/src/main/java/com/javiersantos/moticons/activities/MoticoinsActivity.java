package com.javiersantos.moticons.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.R;
import com.javiersantos.moticons.utils.AppPreferences;
import com.javiersantos.moticons.utils.UtilsDialog;
import com.javiersantos.moticons.utils.UtilsMoticons;
import com.pnikosis.materialishprogress.ProgressWheel;

public class MoticoinsActivity extends AppCompatActivity {
    private static final Integer MOTICOINS_VIDEO = 6;
    private static final Integer MOTICOINS_REMOVE_ADS = 125;

    // UI
    private Toolbar toolbar;
    private Context context;
    private Activity activity;
    private TextView moticoins_amount;
    private CardView moticoins_video;
    private ProgressWheel progressWheel;
    private LinearLayout show_ad;
    private TextView show_ad_description;
    private CardView remove_ads;
    private TextView remove_ads_moticoins;
    private CardView unlock_moticons;
    private TextView unlock_moticons_moticoins;

    // MoticoinsActivity variables
    private AppPreferences appPreferences;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moticoins);
        this.context = this;
        this.activity = (Activity) context;
        this.appPreferences = MoticonsApplication.getAppPreferences();

        initUI();

        progressWheel = (ProgressWheel) findViewById(R.id.progress);
        show_ad = (LinearLayout) findViewById(R.id.show_ad);
        show_ad_description = (TextView) findViewById(R.id.show_ad_description);
        moticoins_amount = (TextView) findViewById(R.id.moticoins_amount);
        moticoins_video = (CardView) findViewById(R.id.moticoins_video);
        remove_ads = (CardView) findViewById(R.id.remove_ads);
        remove_ads_moticoins = (TextView) findViewById(R.id.remove_ads_moticoins);
        unlock_moticons = (CardView) findViewById(R.id.unlock_moticons);
        unlock_moticons_moticoins = (TextView) findViewById(R.id.unlock_moticons_moticoins);

        moticoins_amount.setText(appPreferences.getMoticoins().toString());
        moticoins_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
        moticoins_amount.setCompoundDrawablePadding(14);

        if (appPreferences.getRemovedAds()) {
            remove_ads.setOnClickListener(null);
            remove_ads_moticoins.setText(getResources().getString(R.string.unlocked));

        } else {
            remove_ads_moticoins.setText(MOTICOINS_REMOVE_ADS.toString());
            remove_ads_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
            remove_ads_moticoins.setCompoundDrawablePadding(4);

            remove_ads.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UtilsMoticons.canBuyWithMoticoins(MOTICOINS_REMOVE_ADS)) {
                        appPreferences.setMoticoins(appPreferences.getMoticoins() - MOTICOINS_REMOVE_ADS);
                        moticoins_amount.setText(appPreferences.getMoticoins().toString());
                        appPreferences.setRemoveAds(true);
                        MainActivity.updateMoticoins(context);
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        remove_ads.setOnClickListener(null);
                        remove_ads_moticoins.setText(getResources().getString(R.string.unlocked));
                        remove_ads_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_not_bought)).show();
                    }
                }
            });
        }

        if (appPreferences.getUnlockAllMoticons()) {
            unlock_moticons.setOnClickListener(null);
            unlock_moticons_moticoins.setText(getResources().getString(R.string.unlocked));
        } else {
            final Integer MOTICOINS_UNLOCK_MOTICONS = UtilsMoticons.retrieveMoticoins(UtilsMoticons.loadMoticons());
            unlock_moticons_moticoins.setText(MOTICOINS_UNLOCK_MOTICONS.toString());
            unlock_moticons_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
            unlock_moticons_moticoins.setCompoundDrawablePadding(4);

            unlock_moticons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UtilsMoticons.canBuyWithMoticoins(MOTICOINS_UNLOCK_MOTICONS)) {
                        appPreferences.setMoticoins(appPreferences.getMoticoins() - MOTICOINS_UNLOCK_MOTICONS);
                        moticoins_amount.setText(appPreferences.getMoticoins().toString());
                        appPreferences.setUnlockAllMoticons(true);
                        MainActivity.updateMoticoins(context);
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        unlock_moticons.setOnClickListener(null);
                        unlock_moticons_moticoins.setText(getResources().getString(R.string.unlocked));
                        unlock_moticons_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_not_bought)).show();
                    }
                }
            });
        }

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-0459828968162938/8863489672");

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                appPreferences.setMoticoins(appPreferences.getMoticoins() + MOTICOINS_VIDEO);
                moticoins_amount.setText(appPreferences.getMoticoins().toString());
                MainActivity.updateMoticoins(context);
                requestNewInterstitial();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adAvailable(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                adAvailable(false);
            }
        });

        requestNewInterstitial();

        moticoins_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    UtilsDialog.showSnackbar(activity, getResources().getString(R.string.moticoins_ad_not_available)).show();
                }
            }
        });

    }

    private void initUI() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.moticoins);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(MoticonsApplication.getTestDevice())
                .build();

        show_ad.setVisibility(View.GONE);
        progressWheel.setVisibility(View.VISIBLE);
        interstitialAd.loadAd(adRequest);
    }

    private void adAvailable(Boolean available) {
        if (available) {
            show_ad_description.setText(getResources().getString(R.string.moticoins_ad_description));
        } else {
            show_ad_description.setText(getResources().getString(R.string.moticoins_ad_not_available));
        }

        progressWheel.setVisibility(View.GONE);
        show_ad.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);
    }

}
