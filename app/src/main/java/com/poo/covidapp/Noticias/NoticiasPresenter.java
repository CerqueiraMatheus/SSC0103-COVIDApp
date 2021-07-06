package com.poo.covidapp.Noticias;

import com.poo.covidapp.Util.Modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiasPresenter implements NoticiasContract.Presenter {

    NoticiasContract.View view;

    public NoticiasPresenter(NoticiasContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        List<Noticia> noticiaList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            noticiaList.add(new Noticia("Uma grande notícia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam in orci porta, accumsan lacus eu, semper risus. Aenean fringilla orci in turpis sollicitudin, eu suscipit felis tincidunt. Donec eleifend nulla nec pulvinar posuere. Nulla faucibus dui nisi, eget rhoncus odio tempor ut. In auctor dolor id leo egestas mattis. Aliquam est quam, tristique at ipsum non, pretium facilisis libero. Curabitur consequat scelerisque tempus. ", "CartaCapital", "google.com"));
        }
        view.setAdapter(new NoticiasAdapter(noticiaList, view));
    }

    @Override
    public void onItemClick(int position) {
        System.out.println(position);
        view.startNoticia();
    }
}