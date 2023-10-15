package com.example.authz.service;

import com.example.authz.domain.OpaRequestData;
import com.example.authz.domain.OpaResponseData;
import com.example.authz.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.web.client.RestClient;

import java.util.Set;
import java.util.function.Supplier;

public class OpaAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger logger = LoggerFactory.getLogger(OpaAuthorizationManager.class);

    private final String opaUri;

    public OpaAuthorizationManager(String opaUri) {
        this.opaUri = opaUri;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        return request(context.getRequest());
    }

    private AuthorizationDecision request(HttpServletRequest request) {
        RestClient restClient = RestClient.builder().build();
        ResponseEntity<OpaResponseData> response = restClient
                .post()
                .uri(opaUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createOpaRequestPayload(request))
                .retrieve()
                .toEntity(OpaResponseData.class);
        return toDecision(request, response);
    }

    private OpaRequestData createOpaRequestPayload(HttpServletRequest request) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        /*
        logger.info("-----> Auth is " + token);
        for (Map.Entry<String, Object> entry : token.getTokenAttributes().entrySet()) {
            System.out.println("-----> " + entry.getKey() + " : " + entry.getValue() + " --- " + entry.getValue().getClass());
        }
        */
        Set<String> roles = JwtUtil.extractRoles(token);
        // logger.info("-----> Roles: {}", roles);
        String companyId = JwtUtil.extractCompanyId(token);
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        String path = request.getRequestURI();
        return new OpaRequestData(method, path, roles, companyId);
    }

    private AuthorizationDecision toDecision(HttpServletRequest request, ResponseEntity<OpaResponseData> response) {
        if (response.getStatusCode().value() != 200 || response.getBody() == null) {
            return new AuthorizationDecision(false);
        }
        boolean granted = response.getBody().isResult();
        logger.info("Access to {} {} was {}", request.getMethod(), request.getRequestURI(), granted ? "granted" : "NOT granted");
        return new AuthorizationDecision(granted);
    }
}
