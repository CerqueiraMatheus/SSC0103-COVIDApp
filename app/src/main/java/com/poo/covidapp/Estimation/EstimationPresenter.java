package com.poo.covidapp.Estimation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class EstimationPresenter implements EstimationContract.Presenter {
    private final Context context;
    private final EstimationContract.View view;

    // States initials
    private final List<String> statesInitials = Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE",
            "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO");

    // States names
    private final List<String> statesNames = Arrays.asList("Acre", "Alagoas", "Amapá", "Amazonas",
            "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão",
            "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná",
            "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul",
            "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins");

    // States health sites
    private final List<String> statesHealthSites = Arrays.asList("http://saude.acre.gov.br/",
            "https://www.saude.al.gov.br/", "https://saude.portal.ap.gov.br/",
            "http://www.saude.am.gov.br/", "http://www.saude.ba.gov.br/", "http://www.saude.ce.gov.br/",
            "https://www.saude.df.gov.br/", "https://saude.es.gov.br/", "https://www.saude.go.gov.br/",
            "https://www.saude.ma.gov.br/", "http://www.saude.mt.gov.br/", "http://www.saude.ms.gov.br/",
            "https://www.saude.mg.gov.br/", "http://www.saude.pa.gov.br/", "https://paraiba.pb.gov.br/diretas/saude",
            "https://www.saude.pr.gov.br/", "http://portal.saude.pe.gov.br/", "http://www.saude.pi.gov.br/",
            "https://www.saude.rj.gov.br/", "http://www.saude.rn.gov.br/", "https://saude.rs.gov.br/inicial",
            "http://www.rondonia.ro.gov.br/sesau/", "https://saude.rr.gov.br/", "https://www.saude.sc.gov.br/",
            "http://www.saude.sp.gov.br/", "https://www.saude.se.gov.br/", "https://www.to.gov.br/saude/"
    );


    public EstimationPresenter(EstimationContract.View view, Context context) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.populateView(statesNames);
    }

    @Override
    // Execute the submit
    public void onSubmit(int age, String state, boolean isPNI) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://quandovouservacinado.com/";

        // POST request to get result
        StringRequest request = new StringRequest(Request.Method.POST, url,
                // Handle response
                response -> view.showResult(
                        "Sua previsão",
                        Objects.requireNonNull((Jsoup.parse(response)).selectFirst("h3")).text(),
                        new Intent(Intent.ACTION_VIEW, Uri.parse(
                                statesHealthSites
                                        .get(statesNames.indexOf(state))
                                        .toLowerCase(Locale.ROOT)
                        )
                        )
                ),

                // Handle error
                error -> System.out.println("Houve um erro!")
        ) {
            @Override
            // Setting custom header
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/x-www-form-urlencoded");
                return header;
            }

            @NonNull
            @Override
            // Setting body
            protected Map<String, String> getParams() {
                Map<String, String> body = new HashMap<>();
                body.put("age", String.valueOf(age));
                body.put("state",
                        statesInitials.get(statesNames.indexOf(state)).toLowerCase(Locale.ROOT));
                if (isPNI)
                    body.put("pni", "on");
                return body;
            }
        };
        queue.add(request);
    }
}
