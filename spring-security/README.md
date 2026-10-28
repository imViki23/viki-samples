# FAQs

## Spring Security implementation steps 

- Add `spring-boot-starter-security` library to `build.gradle`. 
- Create a config class and annotate it with the `@EnableMethodSecurity` annotation. In the config class, create a bean for `SecurityFilterChain`.
- In the `SecurityFilterChain` bean method, enter the URL which requires authentication and the URL which requires bypass.
- If the server is a resource server, then it has to do authorization by validating the JWT token passed to the server. To do that.
    - Create a custom filter by extending the `OncePerRequestFilter`.
    - Override `doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)` in `OncePerRequestFilter` class to retrieve JWT token from request object and validate it, if the token is bad token then use response object and return `403`.
    - Register this filter as `http.addFilterBefore(appAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)` in `SecurityFilterChain` bean method. 
    - Sometimes, the role from the JWT token can be used to populate the `Authentication` object, and that role from the authentication object will be used by `@PreAuthorize` annotation.
- If the server is an authentication server (or you can also call it an identity server), it is going to really validate the username and password. Here, there is no concept called filter. Instead, we need a concept called authentication provider.
    - There are different authentication providers.
    - In your case, if you are storing the username and password in your own PostgreSQL table, then you have to use the `DaoAuthenticationProvider` class.
    - The `DaoAuthenticationProvider` expects a `UserDetailsService` object. In our case, we have to create our own `UserDetailsService` by implementing it. The `UserDetailsService` interface contains a `loadUserByUsername` method, which we can override. Inside the `loadUserByUsername` method, we will use the `UserRepository` to fetch the details from the user table.
    - Register this provider as `http.authenticationProvider(authenticationProvider)` in `SecurityFilterChain` bean method.
