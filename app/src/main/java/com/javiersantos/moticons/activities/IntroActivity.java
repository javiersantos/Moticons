package com.javiersantos.moticons.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.javiersantos.moticons.MoticonsApplication;
import com.javiersantos.moticons.slides.FirstSlide;
import com.javiersantos.moticons.slides.FourthSlide;
import com.javiersantos.moticons.slides.SecondSlide;
import com.javiersantos.moticons.slides.ThirdSlide;
import com.javiersantos.moticons.utils.AppPreferences;
import com.javiersantos.moticons.utils.UtilsDialog;

import com.javiersantos.moticons.R;

public class IntroActivity extends AppIntro2 {
    private AppPreferences appPreferences;
    private Context context;

    @Override
    public void init(Bundle savedInstanceState) {
        appPreferences = MoticonsApplication.getAppPreferences();
        context = this;

        addSlide(new FirstSlide());
        addSlide(new SecondSlide());
        addSlide(new ThirdSlide());
        addSlide(new FourthSlide());

        //setSeparatorColor(getResources().getColor(R.color.primary_dark));

        setVibrate(true);
        setVibrateIntensity(30);
    }

    /*@Override
    public void onSkipPressed() {
        AlertDialog.Builder exitDialog = UtilsDialog.showDialog(context, context.getResources().getString(R.string.slide_exit), context.getResources().getString(R.string.slide_exit_description));
        exitDialog.setPositiveButton(context.getResources().getString(R.string.button_continue), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        exitDialog.setNegativeButton(context.getResources().getString(R.string.button_exit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                appPreferences.setFirstRun(false);
                finish();
            }
        });

        exitDialog.show();
    }*/

    @Override
    public void onDonePressed() {
        appPreferences.setFirstRun(false);
        if (appPreferences.getMoticoins() == 0 && appPreferences.getMoticonTimes().size() == 0 && appPreferences.getMoticonUnlocked().size() == 0) {
            appPreferences.setMoticoins(MoticonsApplication.getInitialMoticoins());
        }
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {}

}
