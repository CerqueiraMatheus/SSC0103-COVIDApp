package com.poo.covidapp.Charts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chart {
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
    private int cases;
    @SerializedName("confirmed_per_100k_inhabitants")
    @Expose
    private double casesPer100k;
    @SerializedName("deaths")
    @Expose
    private int deaths;

    @Override
    public String toString() {
        return String.format("Initials: %s\nCases: %d\nCasesPer100k: %f\nDeaths: %d\n",
                             initials, cases, casesPer100k, deaths);
    }


    /* Getters and Setters */

    public String getInitials() {
        return initials;
    }

    public int getCases() {
        return cases;
    }

    public double getCasesPer100k() {
        return casesPer100k;
    }

    public int getDeaths() {
        return deaths;
    }
}
