package com.poo.covidapp.About;

public class AboutPresenter implements AboutContract.Presenter {
    private AboutContract.View view;

    public AboutPresenter(AboutContract.View view) {
        view.setPresenter(this);
        this.view = view;
    }

    @Override
    public void start() {
        String[] titles = new String[3];
        String[] descriptions = new String[3];

        titles[0] = "As expressões da pandemia";
        descriptions[0] = "Descrição";

        titles[1] = "O projeto";
        descriptions[1] = "Descrição";

        titles[2] = "Os autores";
        descriptions[2] = "Descrição";

        view.populateView(titles, descriptions);
    }
}
