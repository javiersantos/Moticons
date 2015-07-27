package com.javiersantos.moticons.slides;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.javiersantos.moticons.R;

public class FirstSlide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_card, container, false);

        final Vibrator vibrator = (Vibrator) container.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        TextView slide_title = (TextView) v.findViewById(R.id.slide_title);
        TextView slide_description = (TextView) v.findViewById(R.id.slide_description);
        CardView slide_card = (CardView) v.findViewById(R.id.slide_card);
        final TextView slide_card_moticon = (TextView) v.findViewById(R.id.slide_card_moticon);
        final TextView slide_card_label = (TextView) v.findViewById(R.id.slide_card_label);
        final EditText slide_card_paste = (EditText) v.findViewById(R.id.slide_card_paste);

        slide_title.setText(getResources().getString(R.string.slide_1));
        slide_description.setText(getResources().getString(R.string.slide_1_description));

        slide_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clipData;

                ClipboardManager clipboardManager = (ClipboardManager) container.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipData = ClipData.newPlainText("text", slide_card_moticon.getText());
                clipboardManager.setPrimaryClip(clipData);

                vibrator.vibrate(200);
                slide_card_moticon.setVisibility(View.GONE);
                slide_card_paste.setVisibility(View.VISIBLE);
            }
        });

        slide_card_paste.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals(slide_card_moticon.getText())) {
                    slide_card_label.setVisibility(View.VISIBLE);
                    slide_card_paste.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return v;
    }

}
