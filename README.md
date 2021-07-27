# COVIDApp

> O COVIDApp é um aplicativo Android desenvolvido como projeto final da disciplina de Programação Orientada a Objetos ([SSC0103](https://uspdigital.usp.br/jupiterweb/obterDisciplina?sgldis=SSC0103&codcur=55041&codhab=0)), ministrada pelo professor Márcio Eduardo Delamaro no ICMC/USP durante o primeiro semestre de 2021.

[Clique aqui para baixar a última versão compilada.](/app/release/app-release.apk)

## Motivação

A desinformação durante o período pandêmico tem sido um dos maiores agravantes para a disseminação e contágio pelo Novo Coronavírus no Brasil. Veículos de imprensa mal intencionados, informações e discursos descontextualizados, e dados fraudados e mal interpretados são apenas alguns dos exemplos de tal fenômeno.

Pensando nisso, concebeu-se a ideia de construção de um sistema capaz de agregar informações de veículos de imprensa confiáveis, dados e gráficos de bases verificadas (ou oficiais) e conceitos básicos para o entendimento da pandemia e que, principalmente, fosse de fácil uso ao usuário final.

## Objetivos

- Popularizar o acesso a informações confiáveis sobre a pandemia do Novo Coronavírus.
- Centralizar informações úteis, simplificar estimativas e dados relativos à pandemia do Novo Coronavírus.
- Aplicar os conceitos de orientação a objeto, strings, arrays e métodos estudados.
- Estender o escopo da disciplina à construção de aplicações Android.

## Funcionalidades implementadas

### Últimas notícias

A seção de últimas notícias é a primeira a ser vista ao iniciar o aplicativo. Nela, é possível acompanhar notícias sobre a COVID-19 no Brasil através de fontes curadas. Ao entrar nesta seção, o aplicativo realiza uma requisição para a [News API](https://github.com/KwabenBerko/News-API-Java); a resposta, então, é enviada para um modelo interno de Notícia, contendo os dados relevantes para exibição. Para cada notícia, também é recebida uma URL que é convertida numa imagem no formato thumbnail através da biblioteca [Picasso](https://square.github.io/picasso/).

<p float="left" align="center">
  <img src="/screenshot/news/1.png" width="250" />
</p>

### Últimos dados

A página dos últimos dados é apresentada com três botões para selecionar que tipo de gráfico deseja ser visto. Ao selecionar uma das opções, é feita uma requisição para API [Brasil.io](https://github.com/turicas/covid19-br/blob/master/api.md) que retorna um JSON com os dados mais recentes. A resposta, então, é organizada em um objeto através do conversor [Gson](https://github.com/google/gson) e formatada em um gráfico com o auxílio da biblioteca [MP Android Chart](https://github.com/PhilJay/MPAndroidChart).

<p align="center">
  <img src="/screenshot/charts/2.png" height="250"/>
</p>
<p align="center">
  <img src="/screenshot/charts/3.png" height="250"/>
</p>
<p align="center">
  <img src="/screenshot/charts/4.png" height="250"/>
</p>

### Estimador de vacinação

A seção do estimador de vacinação apresenta um fluxo de três telas com um formulário que recebe informações de idade, estado e se usuário faz parte do grupo prioritário de imunização. Ao confirmar a operação, é feita uma requisição HTTP ao site [Quando vou ser vacinado?](https://quandovouservacinado.com/), que retorna uma página HTML. A página, por sua vez, é tratada com a biblioteca [JSoup](https://jsoup.org/) e, assim, a mensagem de estimativa é exibida.

<p float="left" align="center">
  <img src="/screenshot/estimation/1.png" width="250" />
  <img src="/screenshot/estimation/2.png" width="250" />
  <img src="/screenshot/estimation/3.png" width="250" />
</p>

### Dicionário de termos

A aba de informações "sobre" contém um glossário de termos da pandemia, construído de forma estática, baseado num [documento do governo do Rio Grande do Sul](http://www.susepe.rs.gov.br/upload/1590671786_Gloss%C3%A1rio%20termos%20Covid.pdf). Além disso, há duas seções de informações sobre os autores e o projeto, também estáticas, porém escritos em formato Markdown, e interpretadas através da biblioteca [MarkWon](https://github.com/noties/Markwon).

<p float="left" align="center">
  <img src="/screenshot/about/1.png" width="250" />
  <img src="/screenshot/about/2.png" width="250" />
  <img src="/screenshot/about/3.png" width="250" />
</p>



## Autores
[Gustavo Henrique Brunelli](https://github.com/GBrunelli)

[João Guilherme Jarochinski Marinho](https://github.com/jj-marinho)

[Matheus Henrique de Cerqueira Pinto](https://github.com/CerqueiraMatheus)

[Pedro Lucas de Moliner de Castro](https://github.com/pedrolmcastro)