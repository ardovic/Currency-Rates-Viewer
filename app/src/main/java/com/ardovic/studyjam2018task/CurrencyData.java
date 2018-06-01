package com.ardovic.studyjam2018task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CurrencyData {

    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies;
    @SerializedName("operationId")
    @Expose
    private String operationId;

}

@Data
class Currency {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ratesByDate")
    @Expose
    private List<RatesByDate> ratesByDate;

}

@Data
class CurrencyRate {

    @SerializedName("sellRate")
    @Expose
    private Float sellRate;
    @SerializedName("buyRate")
    @Expose
    private Float buyRate;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("rate")
    @Expose
    private Float rate;

}

@Data
class RatesByDate {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("currencyRates")
    @Expose
    private List<CurrencyRate> currencyRates;

}
