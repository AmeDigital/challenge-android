## Challenge Ame Digital

## Features
- Home
- Sobre
- Lista de de produtos por categoria
- Detalhe do produto
## Instalação
Para compilar o APK(debug) e instalá-lo imediatamente em um emulador em execução ou dispositivo conectado, abra uma linha de comando e navegue para a raiz do diretório do projeto — no Android Studio, selecione View > Tool Windows > Terminal.
Execute a seguinte comando:
```sh
$ ./gradlew installDebug
```
## Execução dos testes
```sh
$ ./gradlew test
```
## Execução do Detekt (Static code analysis for Kotlin)
```sh
$ ./gradlew detekt
```
Obs: os relatorios em detekt.html e detekt.xml são gerados no diretório root.

## Arquitetura
Foi utilizado MVVM com Android Architecture Components (LiveData e ViewModel)

## Bibliotecas

| Plugin | Objetivo |
| ------ | ------ |
| Retrofit | Requisições da Api |
| Gson | Deserializar retorno da API de formato Json |
| Mockk | Mock para realização de testes |
| Glide | Imagem loading   |
| Detekt | Análise estática de código  |
| kotlin Coroutines Adapter | Uso retrofit com coroutines  |
| Koin | Injeção de dependência |

# Author
 Wagner Messias - wmessiascavalcanti@gmail.com

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Wagner%20Messias-blue.svg)](https://www.linkedin.com/in/wagnermessias/)