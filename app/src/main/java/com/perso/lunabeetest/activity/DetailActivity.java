package com.perso.lunabeetest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.perso.lunabeetest.R;
import com.perso.lunabeetest.bean.UnsplashCard;

/**
 * Created by arnaud on 23/08/16.
 */
public class DetailActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        UnsplashCard detailCard = (UnsplashCard) getIntent().getParcelableExtra("myCard");

        imageView = (ImageView) findViewById(R.id.imageViewDetail);

        Glide.with(this)
                .load(detailCard.getImageUrl())
                .into(imageView);
    }
}
