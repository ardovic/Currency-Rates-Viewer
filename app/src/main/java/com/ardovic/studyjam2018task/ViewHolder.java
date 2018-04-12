package com.ardovic.studyjam2018task;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time_stamp)
    TextView timeStamp;
    @BindView(R.id.description_1)
    TextView description1;
    @BindView(R.id.info1)
    TextView info1;
    @BindView(R.id.description_2)
    TextView description2;
    @BindView(R.id.info2)
    TextView info2;

    public ViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }

    public void bindView(Currency currency) {

        RatesByDate rates = currency.getRatesByDate().get(0);

        title.setText(currency.getCode() + " " + currency.getDescription());
        timeStamp.setText(rates.getDate().substring(0, rates.getDate().indexOf('T')));
        description1.setText(rates.getCurrencyRates().get(0).getCode() + " | " + rates.getCurrencyRates().get(0).getDescription());
        info1.setText("Sell rate: " + rates.getCurrencyRates().get(0).getSellRate() + " | Buy rate: " + rates.getCurrencyRates().get(0).getBuyRate());
        description2.setText(rates.getCurrencyRates().get(1).getCode() + " " + rates.getCurrencyRates().get(1).getDescription());
        info2.setText("Rate: " + rates.getCurrencyRates().get(1).getRate());
    }

}
