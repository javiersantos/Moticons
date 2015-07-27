package com.javiersantos.moticons.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.javiersantos.moticons.R;
import com.javiersantos.moticons.utils.UtilsApp;

public class AboutActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.context = this;

        initUI();
        setAboutScreen();

    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.action_about);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setAboutScreen() {
        TextView app_name = (TextView) findViewById(R.id.app_name);
        CardView card_about_1 = (CardView) findViewById(R.id.card_about_1);
        CardView card_about_2 = (CardView) findViewById(R.id.card_about_2);

        app_name.setText(getResources().getString(R.string.app_name) + " " + UtilsApp.getAppVersionName(getApplicationContext()) + " \"" + getResources().getString(R.string.app_codename) + "\"");

        card_about_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(UtilsApp.goToGooglePlus("+JavierSantos"));
            }
        });
        card_about_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(UtilsApp.goToGooglePlus("+OsamaAsifTBKiller"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_forward, R.anim.slide_out_right);
    }

}
