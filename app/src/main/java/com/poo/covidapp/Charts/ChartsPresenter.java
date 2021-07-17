package com.poo.covidapp.Charts;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChartsPresenter implements ChartsContract.Presenter {
    private ChartsContract.View view;


    /* Create */

    public ChartsPresenter(ChartsContract.View view) {
        this.view = view;
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
        String url = "https://brasil.io/api/dataset/covid19/caso/data?is_last=True&place_type=state";
        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Inside response");
                    Chart chart = new Gson().fromJson(response.toString(), Chart.class);

                    for (State state : chart.states) {
                        System.out.println(state);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Erro na requisição!");
                }
            }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Token "); // Adicionar Token da API
                return header;
            }
        };
    }
}
