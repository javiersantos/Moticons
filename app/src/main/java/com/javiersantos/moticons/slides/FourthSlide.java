package com.javiersantos.moticons.slides;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javiersantos.moticons.R;

public class FourthSlide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.slide_card, container, false);

        TextView slide_title = (TextView) v.findViewById(R.id.slide_title);
        TextView slide_description = (TextView) v.findViewById(R.id.slide_description);
        CardView slide_card = (CardView) v.findViewById(R.id.slide_card);

        slide_title.setText(getResources().getString(R.string.slide_4));
        slide_description.setText(getResources().getString(R.string.slide_4_description));

        slide_card.setVisibility(View.GONE);

        return v;
    }

}
