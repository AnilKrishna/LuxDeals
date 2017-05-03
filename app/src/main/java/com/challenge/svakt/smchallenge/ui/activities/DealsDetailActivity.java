package com.challenge.svakt.smchallenge.ui.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.svakt.smchallenge.R;
import com.challenge.svakt.smchallenge.dagger.DaggerInjector;
import com.challenge.svakt.smchallenge.model.pojo.Deals;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by svakt on 01/05/2017.
 */

public class DealsDetailActivity extends AppCompatActivity {

    public static final String DEAL_ITEM = "DEAL_ITEM";
    public static final String IMG_URL = "https://luxuryescapes.com";
    private Deals dealItem;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.deal_image)
    ImageView dealImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.deal_detail)
    TextView txtDealDetail;

    @BindView(R.id.actual_price)
    TextView txtActualPrice;

    @BindView(R.id.deal_price)
    TextView txtDealPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DaggerInjector.get().inject(this);
        ButterKnife.bind(this);

       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getBundleExtra(DEAL_ITEM);
        dealItem = bundle.getParcelable(DEAL_ITEM);
        toolbar.setTitle(dealItem.getOfferDescription());
        txtDealDetail.setText(dealItem.getName());
        txtActualPrice.setText(dealItem.getValue());
        txtDealPrice.setText(dealItem.getPrice());
        if(dealItem.getImage_app() != null){
            Picasso.with(getApplicationContext()).load(IMG_URL+dealItem.getImage_app().getImage().getBase_uri()+
                    dealItem.getImage_app().getImage().getBase_url_identifier()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(dealImage);
        } else {
            Picasso.with(getApplicationContext()).load(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(dealImage);
        }
    }
}
