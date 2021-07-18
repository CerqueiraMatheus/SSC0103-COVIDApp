package com.poo.covidapp.Charts;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.poo.covidapp.R;
import com.poo.covidapp.Util.Modelo.Chart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChartsPresenter implements ChartsContract.Presenter {
    private ChartsContract.View view;
    private Context context;

    /* Create */

    public ChartsPresenter(ChartsContract.View view, Context context) {
        this.view = view;
        this.context = context;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        String[] titles = new String[3];
        String[] descriptions = new String[3];

        titles[0] = "Número de Casos";
        descriptions[0] = "Número de casos de Covid-19 confirmados agrupados por estado";

        titles[1] = "Número de Casos por 100.000";
        descriptions[1] = "Número de casos de Covid-19 confirmados por 100.000 habitantes agrupados por estado";

        titles[2] = "Número de Mortes";
        descriptions[2] = "Número de mortes causadas pelo Covid-19 agrupados por estado";

        view.setButtons(titles, descriptions);
    }


    /*  */

    @Override
    public void requestChart(Chart.Types type) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.brasil.io/v1/dataset/covid19/caso/data?is_last=True&place_type=state";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Chart chart = new Gson().fromJson(response.toString(), Chart.class);
            chart.setType(type);
            Arrays.sort(chart.getStates());
            view.startChartsActivity(chart.getInitials(), chart.getValues());
        }, error -> {
            System.out.println("Error");
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", context.getString(R.string.username));
                header.put("Authorization", context.getString(R.string.authorization));
                return header;
            }
        };

        queue.add(request);
    }
}

