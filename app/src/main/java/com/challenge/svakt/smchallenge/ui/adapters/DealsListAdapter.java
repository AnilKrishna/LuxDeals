package com.challenge.svakt.smchallenge.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.svakt.smchallenge.R;
import com.challenge.svakt.smchallenge.model.pojo.Deals;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by svakt on 01/05/2017.
 */

public class DealsListAdapter extends RecyclerView.Adapter<DealsListAdapter.ViewHolder> {
    private List<Deals> deals;
    private List<Deals> filterDeals;
    private Context context;
    private CustomFilter mFilter;
    private final String IMG_URL = "https://luxuryescapes.com";

    public DealsListAdapter() {
        this.deals = new ArrayList<>();
        mFilter = new CustomFilter(DealsListAdapter.this);
    }

    public void addDeals(List<Deals> newDeals) {
        filterDeals = newDeals;
        deals.addAll(newDeals);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        this.context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.dealTitle.setText(deals.get(position).getOfferDescription());
        viewHolder.dealDesciption.setText(deals.get(position).getName());
        viewHolder.dealPrice.setText(deals.get(position).getPrice());
        Picasso.with(context).load(IMG_URL+deals.get(position).getImage_app().getImage().getBase_uri()+
                deals.get(position).getImage_app().getImage().getBase_url_identifier()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.dealImage);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public Filter getFilter() {
        return mFilter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_title)
        TextView dealTitle;
        @BindView(R.id.card_text)
        TextView dealDesciption;
        @BindView(R.id.card_price)
        TextView dealPrice;
        @BindView(R.id.card_image)
        ImageView dealImage;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public List<Deals> dealsGetter(){
        return deals;
    }

    public class CustomFilter extends Filter {
        private DealsListAdapter mAdapter;
        public CustomFilter(DealsListAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            deals.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                deals.addAll(filterDeals);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Deals mOffers : filterDeals) {
                    if (mOffers.getOfferDescription().toLowerCase().startsWith(filterPattern)) {
                        deals.add(mOffers);
                    }
                }
            }
            results.values = deals;
            results.count = deals.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.mAdapter.notifyDataSetChanged();
        }
    }


}
