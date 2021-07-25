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
        descriptions[0] = "Acesse um glossário para pesquisar alguns dos termos mais usados durante a pandemia";

        titles[1] = "O projeto";
        descriptions[1] = "Saiba mais sobre a criação e o desenvolvimento do projeto";

        titles[2] = "Os autores";
        descriptions[2] = "Descubra quem está por trás dos panos :)";

        view.populateView(titles, descriptions);
    }
}
