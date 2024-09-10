# NYTNews

This project showcases a modern approach to [Android](https://www.android.com/)
 application development using [Kotlin](https://kotlinlang.org/), [Jetpack Compose](https://developer.android.com/compose)
 and the latest technology stack and focusing on listing news using [NYT API](https://developer.nytimes.com/).

 ## 📸 Screenshots 

![Screenshot](NewsScreenshots.png)


## 📐 Architecture

- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - is a collection of libraries that help you design robust, testable, and maintainable apps.
- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) - is a software architectural pattern that separates the user interface logic (View) from the application's business logic and data (Model), facilitating separation of concerns using an intermediary component called ViewModel.
- [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) - design principles intended to make software designs more understandable, flexible and maintainable.
- [Clean Architecture](https://developer.android.com/topic/architecture) - is an architectural approach in software projects that ensures code cleanliness, flexibility, and maintainability by separating business logic from external dependencies.


## 🏭 Tech Stacks

**Mainly on:**

- [Kotlin](https://kotlinlang.org/) - Kotlin is a modern but already mature programming language designed to make developers happier. It's concise, safe, interoperable with Java and other languages, and provides many ways to reuse code between multiple platforms for productive programming.

- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Gradle’s Kotlin DSL provides an alternative syntax to the traditional Groovy DSL with an enhanced editing experience in supported IDEs, with superior content assist, refactoring, documentation, and more.

- [KSP](https://developer.android.com/build/migrate-to-ksp) - KSP (Kotlin Symbol Processing) is a Kotlin-first alternative to kapt. KSP analyzes Kotlin code directly, which is up to 2x faster.

- [Version Catalog](https://developer.android.com/build/migrate-to-catalogs) - Gradle version catalogs enable you to add and maintain dependencies and plugins in a scalable way. Using Gradle version catalogs makes managing dependencies and plugins easier when you have multiple modules.


### Patterns 

- [Repository Pattern](https://developer.android.com/topic/architecture) - The Repository Pattern is one of the most popular patterns to create an enterprise level application. It restricts us to work directly with the data in the application and creates new layers for database operations, business logic, and the application's UI.
- [UseCase Pattern](https://caminao.blog/how-to-implement-symbolic-representations/patterns/functional-patterns/use-case-patterns/) - This pattern means to convert and pass user actions to inner layers of the application.


### Dependencies

- [Jetpack](https://developer.android.com/jetpack) :
    - [Android KTX](https://developer.android.com/kotlin/ktx.html) - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    
    - [AndroidX](https://developer.android.com/jetpack/androidx) - major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.

    - [Compose](https://developer.android.com/compose) - is Android's modern UI toolkit that simplifies building native user interfaces. It allows you to design your UI using Kotlin code in a declarative way, making it easier to create and manage UI elements.
    
    - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.

    - [Hilt](https://dagger.dev/hilt/) - provides a standard way to incorporate Dagger dependency injection into an Android application.

    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle status of another component

    - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - is a component and library in Android that simplifies and manages navigation between screens in applications.

    - [Room](https://developer.android.com/training/data-storage/room) - is an Android library that provides an abstraction layer over SQLite to allow for more robust database access while leveraging the power of SQLite database queries.

    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    

- [Retrofit](https://square.github.io/retrofit/) - is a type-safe HTTP client for Android and Java that simplifies the process of making network requests and handling REST APIs by allowing developers to define API interfaces with annotated methods.

- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp) - is a tool used to intercept, modify, and process HTTP requests and responses, enabling customization of network requests by adding customized behaviors.

- [Gson](https://github.com/google/gson) - makes it easy to parse JSON into Kotlin objects.

- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - managing background threads with simplified code and reducing needs for callbacks.

- [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - is a cold asynchronous data stream that sequentially emits values and completes normally or with an exception.

- [Coil](https://github.com/coil-kt/coil) - is an image loading library for Android backed by Kotlin Coroutines.

- [Firebase](https://firebase.google.com/) :
    - [Firebase Auth](https://firebase.google.com/docs/auth/android/start) - provides backend services to help authenticate users in your app.
    - [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) - is a lightweight, realtime crash reporter that helps you track, prioritize, and fix stability issues that erode your app quality.

- [LeakCanary](https://square.github.io/leakcanary/) - is a memory leak detection library for Android.
