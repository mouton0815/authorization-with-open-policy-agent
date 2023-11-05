package com.example.demo.domain;

import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * POST request body to be sent to the Open Policy Agent (OPA).
 * The data is collected from the original REST request and the Keycloak JWT.
 */
public class OpaRequest {
    private static class Input {
        public String method;
        public String path;
        public List<String> roles;
        public List<String> groups;
    }

    public Input input;

    public OpaRequest(HttpMethod httpMethod, String path, List<String> roles, List<String> groups) {
        this.input = new Input();
        this.input.method = httpMethod.name();
        this.input.path = path;
        this.input.roles = roles;
        this.input.groups = groups;
    }
}
