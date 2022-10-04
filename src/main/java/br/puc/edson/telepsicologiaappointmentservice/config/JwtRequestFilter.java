package br.puc.edson.telepsicologiaappointmentservice.config;

import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.TokenValidationRequestDto;
import br.puc.edson.telepsicologiaappointmentservice.entrypoint.dto.TokenValidationResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final RestTemplate restTemplate;

    private final String authApiUrl;

    public JwtRequestFilter(RestTemplate restTemplate, @Value("${api.auth.url}")  String authApiUrl) {
        this.restTemplate = restTemplate;
        this.authApiUrl = authApiUrl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if(request.getRequestURI().contains("actuator")){
            chain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");

        boolean invalidToken = true;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            TokenValidationRequestDto tokenValidationRequest = TokenValidationRequestDto.builder().token(jwtToken).build();
            TokenValidationResponseDto tokenValidationResponse = restTemplate.postForObject(authApiUrl + "/validateToken",tokenValidationRequest, TokenValidationResponseDto.class);
            invalidToken = !Objects.requireNonNull(tokenValidationResponse).getValid();
        }

        if(invalidToken) {
            response.setStatus(401);
            response.sendError(401, "Token inválido");
        } else {
            chain.doFilter(request, response);
        }
    }

}