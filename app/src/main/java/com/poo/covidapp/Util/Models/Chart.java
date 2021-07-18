package com.poo.covidapp.Util.Models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Chart {
    @SerializedName("results")
    @Expose
    private State[] states;
    private Types type;


    /* Getters and Setters */

    public void setType(Types type) {
        this.type = type;
    }

    public State[] getStates() {
        return states;
    }

    public ArrayList<Float> getValues() {
        ArrayList<Float> values = new ArrayList<>();

        for (State state : states) {
            float value = 0;
            switch (type) {
                case CASES:
                    value = (float)state.getCases();
                    break;
                case CASES_PER_100K:
                    value = (float)state.getCasesPer100k();
                    break;
                case DEATHS:
                    value = (float)state.getDeaths();
                    break;
            }

            values.add(value);
        }

        return values;
    }

    public ArrayList<String> getInitials() {
        ArrayList<String> initials = new ArrayList<>();

        for (State state : states) {
            initials.add(state.initials);
        }

        return initials;
    }


    /* Inner Classes */

    public static enum Types {
        CASES,
        CASES_PER_100K,
        DEATHS
    }

    public static class State implements Comparable<State> {
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


        /* Utilities */

        @Override
        public String toString() {
            return String.format("Initials: %s\nCases: %d\nCasesPer100k: %f\nDeaths: %d\n",
                                 initials, cases, casesPer100k, deaths);
        }

        @Override
        public int compareTo(State other) {
            return initials.compareTo(other.initials);
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
}
