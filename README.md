# CryptoRune

An Android application developed to demonstrate modern development practices, clean architecture, and advanced UI construction with Jetpack Compose. The app fetches and displays cryptocurrency exchange data from the CoinAPI.io.

## Visão Geral do Projeto

CryptoRune é um aplicativo Android desenvolvido para demonstrar práticas modernas de desenvolvimento. Ele é escrito integralmente em Kotlin e segue os princípios da **Clean Architecture** em conjunto com o padrão **MVI (Model-View-Intent)**, oferecendo uma base escalável e de fácil manutenção.

O objetivo é consumir a API da **CoinAPI.io** para exibir uma lista de corretoras de criptomoedas e seus detalhes. Recursos avançados como cabeçalho dinâmico com gráfico, conversão em tempo real de moedas, tela de carregamento com skeleton e o design system **"Pluto"** garantem uma experiência moderna e consistente.

## Screens LIGHT

<img src="https://github.com/user-attachments/assets/cd066322-7c10-4bbf-be11-e8c2d5332953" width="50%">

## Screens DARK

<img src= "https://github.com/user-attachments/assets/76fd6871-f4a4-4461-bfea-b2692a954d69" width="50%">

## Features

### Core Requirements
-   **List Screen**: Displays a list of exchanges with their name, ID, and 24-hour trading volume in USD.
-   **Detail Screen**: Shows detailed information for a selected exchange, including historical data.
-   **Robust Error Handling**: Gracefully handles specific HTTP errors (400, 401, 403, 429, 550), generic errors, and network connectivity issues.

### Enhanced Features Implemented
-   **Dynamic Portfolio Header**: The main screen features a summary card with a custom-drawn sparkline chart, showcasing a portfolio overview.
-   **Real-time Currency Conversion**: Users can select their preferred currency (USD, BRL, EUR, etc.), and portfolio values update accordingly by fetching real-time conversion rates.
-   **Historical Data Chart**: The details screen includes a chart visualizing historical volume data for the selected exchange.
-   **Modern Skeleton Loading UI**: A custom, full-screen skeleton loader that mimics the final layout, providing a seamless transition from loading to content.
-   **Advanced UI Components**: Modern, animated, and interactive UI components built with Jetpack Compose.

## Arquitetura

O projeto segue os princípios da **Clean Architecture**, separando responsabilidades em três camadas principais: Data, Domain e Presentation.

```
Presentation (ViewModel & Compose)
        |
Domain (UseCases)
        |
Data (Repositories -> DataSources)
```

-   **Data Layer**: Responsible for fetching data from remote (Retrofit/CoinAPI) and local sources.
-   **Domain Layer**: Contains the core business logic, executed through `UseCases`.
-   **Presentation Layer**: Implements an **MVI (Model-View-Intent)** pattern using Jetpack Compose for the UI and a `ViewModel` to manage state and handle user intents.

## Tecnologias Utilizadas

-   **Language**: [Kotlin](https://kotlinlang.org/)
-   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
-   **Architecture**:
    -   [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
    -   MVI (Model-View-Intent) Pattern
    -   Modularization
-   **Dependency Injection**: [Koin](https://insert-koin.io/)
-   **Networking**:
    -   [Retrofit](https://square.github.io/retrofit/) for REST API communication.
    -   [OkHttp](https://square.github.io/okhttp/) as the HTTP client.
    -   [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON parsing.
-   **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
-   **Design System**: Includes an in-house design system named **Pluto** (located in the `libraries/design` module) to centralize UI components and ensure a consistent UX.

## Getting Started

### API Credentials

To run the app, you need an API key from [CoinAPI.io](https://www.coinapi.io/). This project uses a secure properties pattern to manage the API key.

1.  **Get a Free API Key**: Visit [CoinAPI.io](https://www.coinapi.io/get-free-api-key?product_id=market-data-api) to get your free key.

2.  **Create `local.properties` file**:
    -   In the root directory of the project, create a file named `local.properties`.
    -   Add your CoinAPI key to this file.

    Example `local.properties`:
    ```properties
    API_KEY="YOUR-API-KEY-HERE"
    ```

### Important Notes
-   **Security**: The `local.properties` file is included in the project's `.gitignore` to prevent your secret keys from being committed to version control. **Never commit your API keys.**

### Como Executar

1.  Clone o repositório em sua máquina.
2.  Configure a chave da API conforme descrito acima.
3.  Abra o projeto no Android Studio e execute o aplicativo.

### Como Rodar os Testes

Execute os testes unitários e instrumentados com:

```bash
./gradlew test
./gradlew connectedAndroidTest
```

### Development Environment

-   Android Studio | Meerkat Feature Drop | 2024.3.2 Patch 1
-   Java JDK 17

## Classes Principais

- **MainActivity**: activity inicial que configura o tema, o grafo de navegação e gerencia a animação da splash screen.
- **Repositories**: abstrações responsáveis por obter ou persistir dados. As implementações `ExchangesRepositoryImpl` e `LocalRepositoryImpl` acessam a rede e o banco local, respectivamente.
- **ViewModel**: controla o estado das telas e responde aos intents de usuário. Exemplos incluem `MainViewModel`, `ExchangesViewModel` e `DetailsViewModel`.

## Credits

This project was developed based on the challenge requirements provided by [Mercado Bitcoin](https://github.com/mercadobitcoin/querosermb).
