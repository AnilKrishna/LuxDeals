package com.challenge.svakt.smchallenge.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.challenge.svakt.smchallenge.R;
import com.challenge.svakt.smchallenge.dagger.DaggerInjector;
import com.challenge.svakt.smchallenge.ui.decorators.DividerItemDecoration;
import com.challenge.svakt.smchallenge.model.DealService;
import com.challenge.svakt.smchallenge.model.DealsAPI;
import com.challenge.svakt.smchallenge.model.DealsList;
import com.challenge.svakt.smchallenge.model.pojo.Deals;
import com.challenge.svakt.smchallenge.ui.adapters.DealsListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.deals_recycler_view)
    RecyclerView dealsRecyclerView;

    @BindView(R.id.search_box)
    EditText searchBox;

    DealsListAdapter dealsListAdapter;

    private ArrayList<Deals> dealsList;
    View ChildView ;
    Deals dealItem;
    int RecyclerViewItemPosition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerInjector.get().inject(this);
        ButterKnife.bind(this);

        initRecyclerView();
        loadDealsFromAPI();
    }


    public void loadDealsFromAPI(){
        final DealService dealService = DealsAPI.getDealsListAPI();
        Call<DealsList> call = dealService.getDealsList();

        call.enqueue(new Callback<DealsList>() {
            @Override
            public void onResponse(Call<DealsList> call, Response<DealsList> response) {
                if(response.isSuccessful()){
                    //dealsList = new ArrayList<>();
                    dealsList = response.body().getDeals();
                    dealsListAdapter.addDeals(dealsList);
                }
            }

            @Override
            public void onFailure(Call<DealsList> call, Throwable t) {

            }
        });


    }

    public void initRecyclerView(){
        dealsRecyclerView.setHasFixedSize(true);
        dealsRecyclerView.setLayoutManager(new LinearLayoutManager(dealsRecyclerView.getContext()));
        dealsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        dealsRecyclerView.addItemDecoration(new DividerItemDecoration(dealsRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        dealsListAdapter = new DealsListAdapter();
        dealsRecyclerView.setAdapter(dealsListAdapter);

        // Adding on item click listener to RecyclerView.
        dealsRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if(ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);

                    dealItem = dealsListAdapter.dealsGetter().get(RecyclerViewItemPosition);
                    Intent intent  = new Intent(MainActivity.this,DealsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("DEAL_ITEM", dealItem);
                    intent.putExtra("DEAL_ITEM",bundle);
                    startActivity(intent);

                    // Showing clicked item value on screen using toast message.
                    //Toast.makeText(MainActivity.this,dealsListAdapter.dealsGetter().get(RecyclerViewItemPosition).getOfferDescription(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dealsListAdapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
