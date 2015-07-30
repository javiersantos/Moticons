package com.javiersantos.moticons.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.plus.PlusShare;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.IncentivizedAd;
import com.javiersantos.moticons.Keys;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.R;
import com.javiersantos.moticons.billing.IabHelper;
import com.javiersantos.moticons.billing.IabResult;
import com.javiersantos.moticons.billing.Inventory;
import com.javiersantos.moticons.billing.Purchase;
import com.javiersantos.moticons.utils.AppPreferences;
import com.javiersantos.moticons.utils.UtilsDialog;
import com.javiersantos.moticons.utils.UtilsMoticons;
import com.pnikosis.materialishprogress.ProgressWheel;

public class MoticoinsActivity extends AppCompatActivity {
    private static final Integer MOTICOINS_VIDEO = 6;
    private static final Integer MOTICOINS_GOOGLEPLUS = 10;
    private static final Integer MOTICOINS_REMOVE_ADS = 225;
    private static Integer MOTICOINS_UNLOCK_MOTICONS = UtilsMoticons.retrieveMoticoins(UtilsMoticons.loadMoticons());
    private static final String INAPP_REMOVE_ADS = "$1.43";
    private static final String INAPP_UNLOCK_MOTICONS = "$0.79";
    private static final String ITEM_SKU_ADS = "com.javiersantos.moticons.inappads";
    private static final String ITEM_SKU_MOTICONS = "com.javiersantos.moticons.inappmoticons";

    // REQUEST CODE
    private static final Integer GOOGLEPLUS_REQUEST_CODE = 0;
    private static final Integer INAPP_ADS_REQUEST_CODE = 10001;
    private static final Integer INAPP_MOTICONS_REQUEST_CODE = 10002;

    // MoticoinsActivity variables
    private AppPreferences appPreferences;

    // UI
    private Toolbar toolbar;
    private Context context;
    private Activity activity;
    private IabHelper mHelper;

    // VIDEO
    private TextView moticoins_amount;
    private CardView moticoins_video;
    private ProgressWheel progressWheel;
    private LinearLayout show_ad;
    private TextView show_ad_description;

    // GOOGLE PLUS
    private CardView card_googleplus;
    private TextView card_googleplus_description;

    // REMOVE ADS (MOTICOINS)
    private CardView remove_ads_moticoins;
    private TextView remove_ads_label_moticoins;

    // REMOVE ADS (INAPP)
    private CardView remove_ads_inapp;
    private TextView remove_ads_label_inapp;

    // UNLOCK MOTICONS (MOTICOINS)
    private CardView unlock_moticons_moticoins;
    private TextView unlock_moticons_label_moticoins;

    // UNLOCK MOTICONS (INAPP)
    private CardView unlock_moticons_inapp;
    private TextView unlock_moticons_label_inapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moticoins);
        this.context = this;
        this.activity = (Activity) context;
        this.appPreferences = MoticonsApplication.getAppPreferences();

        initUI();
        initScreenElements();
        initInAppBilling();

        if (appPreferences.getMoticoinsGooglePlus()) {
            card_googleplus.setVisibility(View.GONE);
        } else {
            card_googleplus_description.setText(String.format(getResources().getString(R.string.googleplus_share_description), MOTICOINS_GOOGLEPLUS));
            card_googleplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shareIntent = new PlusShare.Builder(context)
                            .setType("text/plain")
                            .setText(getResources().getString(R.string.app_name) + ": " + getResources().getString(R.string.app_description))
                            .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.javiersantos.moticons"))
                            .getIntent();

                    startActivityForResult(shareIntent, GOOGLEPLUS_REQUEST_CODE);
                }
            });
        }

        moticoins_amount.setText(appPreferences.getMoticoins().toString());
        moticoins_amount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
        moticoins_amount.setCompoundDrawablePadding(14);

        if (appPreferences.getRemovedAds()) {
            updateScreenElements(1, true);
        } else {
            updateScreenElements(1, false);

            remove_ads_moticoins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UtilsMoticons.canBuyWithMoticoins(MOTICOINS_REMOVE_ADS)) {
                        appPreferences.setMoticoins(appPreferences.getMoticoins() - MOTICOINS_REMOVE_ADS);
                        moticoins_amount.setText(appPreferences.getMoticoins().toString());
                        appPreferences.setRemoveAds(true);
                        MainActivity.updateMoticoins(context);
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        updateScreenElements(1, true);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_not_bought)).show();
                    }
                }
            });
        }

        if (appPreferences.getUnlockAllMoticons()) {
            updateScreenElements(2, true);
        } else {
            updateScreenElements(2, false);

            unlock_moticons_moticoins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (UtilsMoticons.canBuyWithMoticoins(MOTICOINS_UNLOCK_MOTICONS)) {
                        appPreferences.setMoticoins(appPreferences.getMoticoins() - MOTICOINS_UNLOCK_MOTICONS);
                        moticoins_amount.setText(appPreferences.getMoticoins().toString());
                        appPreferences.setUnlockAllMoticons(true);
                        MainActivity.updateMoticoins(context);
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        updateScreenElements(2, true);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_not_bought)).show();
                    }
                }
            });
        }

        HeyzapAds.start(Keys.getHeyZapPublicKey(), activity, HeyzapAds.DISABLE_AUTOMATIC_FETCH);
        setupHeyzapCallbacks();

        requestNewInterstitial();

        moticoins_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IncentivizedAd.isAvailable()) {
                    IncentivizedAd.display(activity);
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

    private void initScreenElements() {
        // VIDEO
        progressWheel = (ProgressWheel) findViewById(R.id.progress);
        show_ad = (LinearLayout) findViewById(R.id.show_ad);
        show_ad_description = (TextView) findViewById(R.id.show_ad_description);
        moticoins_amount = (TextView) findViewById(R.id.moticoins_amount);
        moticoins_video = (CardView) findViewById(R.id.moticoins_video);

        // GOOGLE PLUS
        card_googleplus = (CardView) findViewById(R.id.card_googleplus);
        card_googleplus_description = (TextView) findViewById(R.id.card_googleplus_description);

        // REMOVE ADS (MOTICOINS)
        remove_ads_moticoins = (CardView) findViewById(R.id.remove_ads_moticoins);
        remove_ads_label_moticoins = (TextView) findViewById(R.id.remove_ads_label_moticoins);

        // REMOVE ADS (INAPP)
        remove_ads_inapp = (CardView) findViewById(R.id.remove_ads_inapp);
        remove_ads_label_inapp = (TextView) findViewById(R.id.remove_ads_label_inapp);

        // UNLOCK MOTICONS (MOTICOINS)
        unlock_moticons_moticoins = (CardView) findViewById(R.id.unlock_moticons_moticoins);
        unlock_moticons_label_moticoins = (TextView) findViewById(R.id.unlock_moticons_label_moticoins);

        // UNLOCK MOTICONS (INAPP)
        unlock_moticons_inapp = (CardView) findViewById(R.id.unlock_moticons_inapp);
        unlock_moticons_label_inapp = (TextView) findViewById(R.id.unlock_moticons_label_inapp);
    }

    /**
     * Update the screen cards
     * @param adsOrMoticons 1 for ads, 2 for Moticons
     * @param res true if bought, otherwise false
     */
    private void updateScreenElements(Integer adsOrMoticons, Boolean res) {
        switch (adsOrMoticons) {
            case 1:
                if (res) {
                    remove_ads_moticoins.setOnClickListener(null);
                    remove_ads_label_moticoins.setText(getResources().getString(R.string.unlocked));
                    remove_ads_label_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    remove_ads_inapp.setOnClickListener(null);
                    remove_ads_label_inapp.setText(getResources().getString(R.string.unlocked));
                    remove_ads_label_inapp.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    remove_ads_label_moticoins.setText(MOTICOINS_REMOVE_ADS.toString());
                    remove_ads_label_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
                    remove_ads_label_moticoins.setCompoundDrawablePadding(4);
                    remove_ads_label_inapp.setText(INAPP_REMOVE_ADS);
                    remove_ads_label_inapp.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_shop_white, 0);
                    remove_ads_label_inapp.setCompoundDrawablePadding(4);
                }
                break;
            case 2:
                if (res) {
                    unlock_moticons_moticoins.setOnClickListener(null);
                    unlock_moticons_label_moticoins.setText(getResources().getString(R.string.unlocked));
                    unlock_moticons_label_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    unlock_moticons_inapp.setOnClickListener(null);
                    unlock_moticons_label_inapp.setText(getResources().getString(R.string.unlocked));
                    unlock_moticons_label_inapp.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    unlock_moticons_label_moticoins.setText(MOTICOINS_UNLOCK_MOTICONS.toString());
                    unlock_moticons_label_moticoins.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
                    unlock_moticons_label_moticoins.setCompoundDrawablePadding(4);
                    unlock_moticons_label_inapp.setText(INAPP_UNLOCK_MOTICONS);
                    unlock_moticons_label_inapp.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_shop_white, 0);
                    unlock_moticons_label_inapp.setCompoundDrawablePadding(4);
                }
                break;
        }
    }

    private void initInAppBilling() {
        final IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
            @Override
            public void onConsumeFinished(Purchase purchase, IabResult result) {
                if (result.isSuccess()) {
                    switch (purchase.getSku()) {
                        case ITEM_SKU_ADS:
                            appPreferences.setRemoveAds(true);
                            updateScreenElements(1, true);
                            UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                            break;
                        case ITEM_SKU_MOTICONS:
                            appPreferences.setUnlockAllMoticons(true);
                            updateScreenElements(2, true);
                            UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                            break;
                    }
                } else {
                    UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_inapp_error)).show();
                }
            }
        };

        final IabHelper.QueryInventoryFinishedListener mReceivedInventoryAdsListener = new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                if (result.isFailure()) {
                    UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_inapp_error)).show();
                } else {
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU_ADS), mConsumeFinishedListener);
                }
            }
        };

        final IabHelper.QueryInventoryFinishedListener mReceivedInventoryMoticonsListener = new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                if (result.isFailure()) {
                    UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_inapp_error)).show();
                } else {
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU_MOTICONS), mConsumeFinishedListener);
                }
            }
        };

        final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedAdsListener = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                if (result.isFailure()) {
                    if (result.getResponse() == 7) {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        appPreferences.setRemoveAds(true);
                        updateScreenElements(1, true);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_inapp_error)).show();
                    }
                } else {
                    mHelper.queryInventoryAsync(mReceivedInventoryAdsListener);
                }
            }
        };

        final IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedMoticonsListener = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                if (result.isFailure()) {
                    if (result.getResponse() == 7) {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_bought)).show();
                        appPreferences.setUnlockAllMoticons(true);
                        updateScreenElements(2, true);
                    } else {
                        UtilsDialog.showSnackbar(activity, getResources().getString(R.string.snackbar_inapp_error)).show();
                    }
                } else {
                    mHelper.queryInventoryAsync(mReceivedInventoryMoticonsListener);
                }
            }
        };

        mHelper = new IabHelper(context, Keys.getBase64EncodedPublicKey());
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {}
        });

        remove_ads_inapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHelper.launchPurchaseFlow(activity, ITEM_SKU_ADS, INAPP_ADS_REQUEST_CODE, mPurchaseFinishedAdsListener, "inappremoveadspurchase");
            }
        });

        unlock_moticons_inapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHelper.launchPurchaseFlow(activity, ITEM_SKU_MOTICONS, INAPP_MOTICONS_REQUEST_CODE, mPurchaseFinishedMoticonsListener, "inappunlockmoticonspurchase");
            }
        });

    }

    protected void setupHeyzapCallbacks() {
        HeyzapAds.OnStatusListener statusListener = new HeyzapAds.OnStatusListener() {
            @Override
            public void onShow(String s) {

            }

            @Override
            public void onClick(String s) {

            }

            @Override
            public void onHide(String s) {

            }

            @Override
            public void onFailedToShow(String s) {
                adAvailable(false);
            }

            @Override
            public void onAvailable(String s) {
                adAvailable(true);
            }

            @Override
            public void onFailedToFetch(String s) {
                adAvailable(false);
            }

            @Override
            public void onAudioStarted() {

            }

            @Override
            public void onAudioFinished() {

            }
        };

        IncentivizedAd.setOnStatusListener(statusListener);

        HeyzapAds.OnIncentiveResultListener incentiveResultListener = new HeyzapAds.OnIncentiveResultListener() {
            @Override
            public void onComplete(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        appPreferences.setMoticoins(appPreferences.getMoticoins() + MOTICOINS_VIDEO);
                        moticoins_amount.setText(appPreferences.getMoticoins().toString());
                        MainActivity.updateMoticoins(context);
                        requestNewInterstitial();
                    }
                });
            }

            @Override
            public void onIncomplete(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestNewInterstitial();
                    }
                });
            }
        };

        IncentivizedAd.setOnIncentiveResultListener(incentiveResultListener);
    }

    private void requestNewInterstitial() {
        IncentivizedAd.fetch();

        show_ad.setVisibility(View.GONE);
        progressWheel.setVisibility(View.VISIBLE);
    }

    private void adAvailable(final Boolean available) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (available) {
                    show_ad_description.setText(String.format(getResources().getString(R.string.moticoins_ad_description), MOTICOINS_VIDEO));
                } else {
                    show_ad_description.setText(getResources().getString(R.string.moticoins_ad_not_available));
                }

                progressWheel.setVisibility(View.GONE);
                show_ad.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == GOOGLEPLUS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                appPreferences.setMoticoinsGooglePlus(true);
                card_googleplus.setVisibility(View.GONE);
                appPreferences.setMoticoins(appPreferences.getMoticoins() + MOTICOINS_GOOGLEPLUS);
                moticoins_amount.setText(appPreferences.getMoticoins().toString());
                MainActivity.updateMoticoins(context);
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.dispose();
        }
        mHelper = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);
    }

}
