package com.poo.covidapp.Charts;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poo.covidapp.R;
import com.poo.covidapp.Util.Models.Chart;

import java.util.HashMap;
import java.util.Map;

public class ChartsPresenter implements ChartsContract.Presenter {
    private final ChartsContract.View view;
    private final Context context;
    private final Gson gson;

    public ChartsPresenter(ChartsContract.View view, Context context) {
        this.view = view;
        this.context = context;

        // Set presenter
        view.setPresenter(this);

        // Get instance of Gson
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Override
    public void start() {
        String[] titles = new String[3];
        String[] descriptions = new String[3];

        // Set cases button
        titles[Chart.Types.CASES.ordinal()] = Chart.getTitle(Chart.Types.CASES);
        descriptions[Chart.Types.CASES.ordinal()] = Chart.getDescription(
                Chart.Types.CASES);

        // Set cases per 100k button
        titles[Chart.Types.CASES_PER_100K.ordinal()] = Chart.getTitle(Chart.Types.CASES_PER_100K);
        descriptions[Chart.Types.CASES_PER_100K.ordinal()] = Chart.getDescription(
                Chart.Types.CASES_PER_100K);

        // Set deaths button
        titles[Chart.Types.DEATHS.ordinal()] = Chart.getTitle(Chart.Types.DEATHS);
        descriptions[Chart.Types.DEATHS.ordinal()] = Chart.getDescription(
                Chart.Types.DEATHS);

        view.setButtons(titles, descriptions);
    }

    @Override
    public void requestChart(Chart.Types type) {
        // Get instance of queue
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.brasil.io/v1/dataset/covid19/caso/data?is_last=True&place_type=state";

        // Make request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            // Handle response
            response -> {
                Chart chart = gson.fromJson(response.toString(), Chart.class);
                view.startChartsActivity(Chart.getTitle(type), chart.getEntries(type));
            },

            // Handle error
            error -> System.out.println("Erro na request do gráfico!")
        ) {
            @Override
            // Setting custom header
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", context.getString(R.string.username));
                header.put("Authorization", context.getString(R.string.authorization));
                return header;
            }
        };

        // Add request to queue
        queue.add(request);
    }
}
