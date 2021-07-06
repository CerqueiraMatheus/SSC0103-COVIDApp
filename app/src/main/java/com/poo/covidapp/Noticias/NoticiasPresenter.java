package com.poo.covidapp.Noticias;

import android.content.Intent;
import android.net.Uri;

import com.poo.covidapp.Util.Modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiasPresenter implements NoticiasContract.Presenter {

    NoticiasContract.View view;
    List<Noticia> noticiaList;

    public NoticiasPresenter(NoticiasContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        noticiaList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            noticiaList.add(new Noticia(
                    "Uma grande notícia",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in orci porta, accumsan lacus eu, semper risus. Aenean fringilla orci in turpis sollicitudin, eu suscipit felis tincidunt. Donec eleifend nulla nec pulvinar posuere. Nulla faucibus dui nisi, eget rhoncus odio tempor ut. In auctor dolor id leo egestas mattis. Aliquam est quam, tristique at ipsum non, pretium facilisis libero. Curabitur consequat scelerisque tempus. ",
                    "CartaCapital",
                    "http://www.google.com"));
        }
        view.setAdapter(new NoticiasAdapter(noticiaList, view));
    }

    @Override
    public void onItemClick(int position) {
        view.startNoticia(new Intent(Intent.ACTION_VIEW, Uri.parse(noticiaList.get(position).getUrl())));
    }
}
