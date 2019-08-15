# o Lodjinha - Versão criada por Luiz Zabuscka

Projeto criado na arquitetura MVVM, utilizando Architecture Components e AndroidX.

Cada camada da arquitetura é representada por um módulo no projeto.
- View - módulo app
- ViewModel - módulo viewmodel
- Model - módulo data

Há alguns testes unitários na camada ViewModel e testes instrumentados no módulo app.

## Desenho da Arquitetura:
(Cada elemento é um módulo do projeto)

![alt text](https://ibin.co/4rR2rDCvjjPx.png)

## Bibliotecas utilizadas:
- Koin
- RxJava (RxKotlin)
- LifeCycle
- Paging (do Jetpack)
- Anko (Para simplificar chamadas entre Activities e Dialogs)
- Glide
- Retrofit

Para Testes:
- Mockito (e MockitoKotlin)
- Espresso
