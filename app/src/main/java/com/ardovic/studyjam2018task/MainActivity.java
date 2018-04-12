package com.ardovic.studyjam2018task;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<CurrencyData>{

    ApiInterface mApiInterface;
    CurrencyData mCurrencyData;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    RecyclerView.Adapter<ViewHolder> mAdapter;

    @BindView(R.id.toolbar_actionbar)
    Toolbar mActionBarToolbar;

    @BindView(R.id.toolbar_fetch)
    Button mFetchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mActionBarToolbar);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        mFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFetchButton.setVisibility(View.GONE);
                makeACall();
            }
        });

        makeACall();
    }

    @Override
    public void onResponse(Call<CurrencyData> call, Response<CurrencyData> response) {
        mCurrencyData = response.body();
        displayResult();
    }

    @Override
    public void onFailure(Call<CurrencyData> call, Throwable t) {

    }

    void makeACall() {
        Map<String, String> headers = new HashMap<>();
        headers.put("jmb-protocol-service", "Currency");
        headers.put("jmb-protocol-version", "1.0");

        Call<CurrencyData> call = mApiInterface.getCurrencyData(new CurrenciesRequestData(), headers);
        call.enqueue(MainActivity.this);
    }

    void displayResult() {

        getSupportActionBar().setTitle(mCurrencyData.getBaseCurrency());

        if(mAdapter == null) {
            mAdapter = new RecyclerView.Adapter<ViewHolder>() {
                @NonNull
                @Override
                public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
                }

                @Override
                public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                    holder.bindView(mCurrencyData.getCurrencies().get(position));
                }

                @Override
                public int getItemCount() {
                    return mCurrencyData.getCurrencies().size();
                }
            };
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
        mFetchButton.setVisibility(View.VISIBLE);
    }
}
