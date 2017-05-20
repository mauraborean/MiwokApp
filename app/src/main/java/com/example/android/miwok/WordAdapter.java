package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maura on 5/17/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int viewColorId;

    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects, int viewColor) {
        super(context, 0, objects);
        this.viewColorId = viewColor;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemGroup = convertView;

        if (listItemGroup == null) {
            listItemGroup = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word word = (Word) getItem(position);

        int color = ContextCompat.getColor(getContext(), viewColorId);
        RelativeLayout textContainer = (RelativeLayout) listItemGroup.findViewById(R.id.text_container);
        textContainer.setBackgroundColor(color);

        TextView miwokTranslation = (TextView) listItemGroup.findViewById(R.id.miwok_text_view);
        miwokTranslation.setText(word.getMiwok());

        TextView englishTranslation = (TextView) listItemGroup.findViewById(R.id.default_text_view);
        englishTranslation.setText(word.getEnglish());

        ImageView image = (ImageView) listItemGroup.findViewById(R.id.image);

        if (word.hasImage()) {
            image.setImageResource(word.getImgResourceId());
        } else {
            image.setVisibility(View.GONE);
        }

        return listItemGroup;
    }
}
