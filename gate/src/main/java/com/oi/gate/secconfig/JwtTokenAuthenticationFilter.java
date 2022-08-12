package com.oi.gate.secconfig;


import com.oi.shared.dtos.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;


    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info(request.toString());
        // 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        // 2. validate the header and check the prefix
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);        // If not valid, go to the next filter.
            return;
        }

        // Authenticate Host
        String hostHeader = request.getHeader("FROM");
        String hostKey = request.getHeader("VIA");



        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {    // exceptions might be thrown in creating the claims if for example the token is expired


            String username = verifyThirdParty(token);
            if (username != null) {
                @SuppressWarnings("unchecked")
                List<String> authorities = new ArrayList<>();

                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());

                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated

            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);
    }


    public String verifyThirdParty(String apiKey) {
        Map<String, String> apiKeys = new HashMap<>();
        apiKeys.put("GljO6l6MIube9VR8C11lJURv9hUtewiL", "shopOne");
        apiKeys.put("SyO9DLsBofvpkggDi2OE4Y0562U5N2nn", "shopTwo");

        if (apiKeys.containsKey(apiKey)) {
            return apiKeys.get(apiKey);
        }
        throw new AuthenticationCredentialsNotFoundException("Your key is wrong");
    }

}
