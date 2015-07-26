package com.javiersantos.moticons.slides;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javiersantos.moticons.R;

public class SecondSlide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_card, container, false);

        final Vibrator vibrator = (Vibrator) container.getContext().getSystemService(Context.VIBRATOR_SERVICE);

        TextView slide_title = (TextView) v.findViewById(R.id.slide_title);
        TextView slide_description = (TextView) v.findViewById(R.id.slide_description);
        CardView slide_card = (CardView) v.findViewById(R.id.slide_card);
        final TextView slide_card_moticon = (TextView) v.findViewById(R.id.slide_card_moticon);
        final TextView slide_card_label = (TextView) v.findViewById(R.id.slide_card_label);

        slide_title.setText(getResources().getString(R.string.slide_2));
        slide_description.setText(getResources().getString(R.string.slide_2_description));

        slide_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, slide_card_moticon.getText());
                container.getContext().startActivity(Intent.createChooser(intent, String.format(container.getContext().getResources().getString(R.string.action_share), slide_card_moticon.getText())));

                vibrator.vibrate(200);
                slide_card_label.setVisibility(View.VISIBLE);
                return false;
            }
        });

        return v;
    }

}
