# CryptoRune

An Android application developed to demonstrate modern development practices, clean architecture, and advanced UI construction with Jetpack Compose. The app fetches and displays cryptocurrency exchange data from the CoinAPI.io.

## About the Project

CryptoRune was developed as a technical challenge to showcase skills in modern Android development. The project is built entirely in Kotlin and utilizes a **Clean Architecture** with an **MVI (Model-View-Intent)** pattern, ensuring a scalable, maintainable, and testable codebase.

The application's core function is to consume the **CoinAPI.io** to fetch a list of cryptocurrency exchanges and display their details. Throughout its development, several advanced features were implemented to create a fluid, resilient, and visually appealing user experience. This includes a dynamic portfolio header with a custom-drawn chart, real-time currency conversion, a full-screen skeleton loading state to improve perceived performance, and a custom in-house design system named **"Pluto"** to ensure UI consistency.

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

## Architecture

This project is built upon the principles of **Clean Architecture**, separating concerns into three primary layers: Data, Domain, and Presentation.

-   **Data Layer**: Responsible for fetching data from remote (Retrofit/CoinAPI) and local sources.
-   **Domain Layer**: Contains the core business logic, executed through `UseCases`.
-   **Presentation Layer**: Implements an **MVI (Model-View-Intent)** pattern using Jetpack Compose for the UI and a `ViewModel` to manage state and handle user intents.

## Technologies Used

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

### How to Run

1.  Clone the repository to your local machine.
2.  Set up your API key as described above.
3.  Open the project in Android Studio and run the app.

### Development Environment

-   Android Studio | Meerkat Feature Drop | 2024.3.2 Patch 1
-   Java JDK 17

## Credits

This project was developed based on the challenge requirements provided by [Mercado Bitcoin](https://github.com/mercadobitcoin/querosermb).
