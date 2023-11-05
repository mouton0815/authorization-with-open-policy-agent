package com.example.demo.service;

import com.example.demo.domain.OpaRequest;
import com.example.demo.domain.OpaResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.function.Supplier;

public class OpaAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(OpaAuthorizationManager.class);

    private final String opaUri;

    public OpaAuthorizationManager(String opaUri) {
        this.opaUri = opaUri;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        if (authentication == null || authentication.get() == null) {
            throw new RuntimeException("Authentication object is null");
        }
        HttpServletRequest request = context.getRequest();
        RestClient restClient = RestClient.builder().build();
        ResponseEntity<OpaResponse> response = restClient
                .post()
                .uri(opaUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createOpaRequestPayload(authentication.get(), request))
                .retrieve()
                .toEntity(OpaResponse.class);
        return toDecision(request, response);
    }

    private OpaRequest createOpaRequestPayload(Authentication authentication, HttpServletRequest request) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        /*
        logger.info("-----> Auth is " + token);
        for (Map.Entry<String, Object> entry : token.getTokenAttributes().entrySet()) {
            System.out.println("-----> " + entry.getKey() + " : " + entry.getValue() + " --- " + entry.getValue().getClass());
        }
        */
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        String path = request.getRequestURI();
        List<String> roles = extractClaim(token, "roles");
        List<String> groups = extractClaim(token, "groups");
        return new OpaRequest(method, path, roles, groups);
    }

    private AuthorizationDecision toDecision(HttpServletRequest request, ResponseEntity<OpaResponse> response) {
        if (response.getStatusCode().value() != 200 || response.getBody() == null) {
            return new AuthorizationDecision(false);
        }
        boolean granted = response.getBody().result;
        logger.info("Access to {} {} was {}", request.getMethod(), request.getRequestURI(), granted ? "granted" : "NOT granted");
        return new AuthorizationDecision(granted);
    }

    private List<String> extractClaim(JwtAuthenticationToken token, String claim) {
        @SuppressWarnings({"unchecked"})
        List<String> list = (List<String>) token.getTokenAttributes().get(claim);
        if (list == null) {
            throw new RuntimeException("Token claim '" + claim + "' missing");
        }
        /*
        for (String entry : list) {
            System.out.println("-----> " + claim + " : " + entry);
        }
        */
        return list;
    }
}
