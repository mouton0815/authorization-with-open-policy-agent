package com.example.authz.domain;

import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * POST request body to be sent to the Open Policy Agent (OPA).
 * The data is collected from the original REST request and the Keycloak JWT.
 */
public class OpaRequestData {
    private static class Input {
        public String method;
        public String path;
        public Set<String> roles;
        public String companyId;
    }

    public Input input;

    public OpaRequestData(HttpMethod httpMethod, String path, Set<String> roles, String companyId) {
        this.input = new Input();
        this.input.method = httpMethod.name();
        this.input.path = path;
        this.input.roles = roles;
        this.input.companyId = companyId;
    }
}
