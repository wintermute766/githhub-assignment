#### Architecture & Technical stack:

- MVVM architecture pattern and Clean architecture
- Jetpack Compose for the presentation layer
- Retrofit for network calls
- Hilt for dependency injection
- Coil for image loading

This technical stack is well-suited for small applications as well as for large ones with complex
business logic

#### Implemented features:

- Infinite scrolling & Paging
- Dialog with details about the repository with clickable link
- Image caching by Coil
- Clear UI/UX if there is no internet connection & graceful restoration if the connection reappears

#### Not implemented features:

- Loader on the repository list screen indicating the state of network request
- Favorites screen
- Search

These features can be implemented by creating new ViewModels and Compose screens as Clean
architecture provides an easy scalability 