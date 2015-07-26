package com.javiersantos.moticons.slides;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javiersantos.moticons.R;
import com.javiersantos.moticons.utils.UtilsDialog;

public class ThirdSlide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_card, container, false);

        final Vibrator vibrator = (Vibrator) container.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        TextView slide_title = (TextView) v.findViewById(R.id.slide_title);
        TextView slide_description = (TextView) v.findViewById(R.id.slide_description);
        final CardView slide_card = (CardView) v.findViewById(R.id.slide_card);
        final TextView slide_card_moticon = (TextView) v.findViewById(R.id.slide_card_moticon);
        final TextView slide_card_label = (TextView) v.findViewById(R.id.slide_card_label);

        slide_title.setText(getResources().getString(R.string.slide_3));
        slide_description.setText(getResources().getString(R.string.slide_3_description));

        slide_card_moticon.setText("(/^▽^)/");

        slide_card_label.setText("5");
        slide_card_label.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_stars_white, 0);
        slide_card_label.setCompoundDrawablePadding(4);
        slide_card_label.setVisibility(View.VISIBLE);

        slide_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder confirmUnlock = UtilsDialog.showDialog(container.getContext(), container.getContext().getResources().getString(R.string.moticon_unlock), String.format(container.getContext().getString(R.string.moticon_unlock_description), "5", "5", slide_card_moticon.getText()));
                confirmUnlock.setPositiveButton(container.getContext().getResources().getString(R.string.button_unlock), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        vibrator.vibrate(200);
                        slide_card_label.setText(R.string.slide_awesome);
                        slide_card_label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        slide_card.setOnClickListener(null);
                        dialogInterface.dismiss();
                    }
                });
                confirmUnlock.setNegativeButton(container.getContext().getResources().getString(R.string.button_later), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                confirmUnlock.show();
            }
        });

        return v;
    }

}
