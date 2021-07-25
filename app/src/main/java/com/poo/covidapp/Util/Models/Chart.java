package com.poo.covidapp.Util.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.TreeMap;

public class Chart {
    @SerializedName("results")
    @Expose
    private State[] states;
    private Types type;


    /* Static Methods */

    static public String getTitle(Types type) {
        String title = "";

        switch(type) {
            case CASES:
                title =  "Número de Casos";
                break;
            case CASES_PER_100K:
                title =  "Número de Casos por 100.000";
                break;
            case DEATHS:
                title = "Número de Mortes";
                break;
        }

        return title;
    }

    static public String getDescription(Types type) {
        String description = "";

        switch(type) {
            case CASES:
                description =  "Número de casos de Covid-19 confirmados agrupados por estado.";
                break;
            case CASES_PER_100K:
                description =  "Número de casos de Covid-19 confirmados por 100.000 habitantes agrupados por estado.";
                break;
            case DEATHS:
                description = "Número de mortes causadas pelo Covid-19 agrupados por estado.";
                break;
        }

        return description;
    }


    /* Getters and Setters */

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
    }

    public TreeMap<String, Float> getEntries() {
        TreeMap<String, Float> entries = new TreeMap<>();

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

            entries.put(state.getInitials(), value);
        }

        return entries;
    }


    /* Inner Classes */

    public static enum Types {
        CASES,
        CASES_PER_100K,
        DEATHS
    }

    public static class State {
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
