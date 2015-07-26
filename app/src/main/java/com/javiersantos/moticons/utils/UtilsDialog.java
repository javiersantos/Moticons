package com.javiersantos.moticons.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import com.javiersantos.moticons.R;

public class UtilsDialog {

    public static AlertDialog.Builder showDialog(Context context, String title, String content) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(false);
        return alertDialog;
    }

    public static Snackbar showSnackbar(Activity activity, String text) {
        return Snackbar.make(activity.findViewById(R.id.main_content), text, Snackbar.LENGTH_SHORT);
    }
}
