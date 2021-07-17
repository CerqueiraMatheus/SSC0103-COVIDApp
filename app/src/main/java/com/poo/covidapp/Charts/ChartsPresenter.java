package com.poo.covidapp.Charts;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.poo.covidapp.R;

import org.json.JSONObject;

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

    public void requestChart() {
        System.out.println("Inside method");
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://api.brasil.io/v1/dataset/covid19/caso/data?is_last=True&place_type=state";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null, response -> {
            System.out.println("Inside response");
            Chart chart = new Gson().fromJson(response.toString(), Chart.class);

            for (State state : chart.states) {
                System.out.println(state);
            }
        }, error -> {
            System.out.println("Error");
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", context.getString(R.string.username));
                header.put("Authorization", context.getString(R.string.authorization)); // Adicionar Token da API
                return header;
            }
        };

        System.out.println(request.toString());

        queue.add(request);
    }
}

