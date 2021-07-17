package com.poo.covidapp.Charts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {
    @SerializedName("results")
    @Expose
    public State[] states;
}

class State {
    @SerializedName("state")
    @Expose
    private String initials;
    @SerializedName("confirmed")
    @Expose
    private int confirmedCases;
    @SerializedName("confirmed_per_100k_inhabitants")
    @Expose
    private int confirmedCasesPer1000;
    @SerializedName("deaths")
    @Expose
    private int deaths;

    public String getInitials() {
        return initials;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public int getConfirmedCasesPer1000() {
        return confirmedCasesPer1000;
    }

    public int getDeaths() {
        return deaths;
    }
}
