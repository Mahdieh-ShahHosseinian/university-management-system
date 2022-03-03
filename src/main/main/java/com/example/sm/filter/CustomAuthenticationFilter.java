package com.example.sm.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.sm.model.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.sm.security.SecurityConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/ums.com/api/v4/login");
    }

    /**
     * This methode is for when the client sends a credentials and the server wants to validate that credentials
     *
     * @param request  credentials
     * @param response validation
     * @return authenticate
     * @throws AuthenticationException RuntimeException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("attemptAuthentication");

//        // Parameter format
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info("Username is {} and Password is {}", username, password);

        // Json  format
        try {

            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            String username = applicationUser.getUsername();
            String password = applicationUser.getPassword();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This methode will be invoked after the {@link #attemptAuthentication(HttpServletRequest, HttpServletResponse)} is successful, and
     * if {@link #attemptAuthentication(HttpServletRequest, HttpServletResponse)} fails, this will never be executed
     * <p>
     * This method will create a token and send it to the client
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        log.info("successfulAuthentication");

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC512(SECRET.getBytes());

        String accessToken = JWT.create()
                .withSubject(applicationUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .withIssuedAt(new Date())
                .withClaim(CLAIM_KEY, applicationUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

//        // Parameter format
//        response.setHeader("accessToken", accessToken);

        // Json format
        Map<String, String> tokens = new HashMap<>();
        tokens.put("Access-Token", accessToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
