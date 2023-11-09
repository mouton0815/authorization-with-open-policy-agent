package com.example.demo.domain;

import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * POST request body to be sent to the Open Policy Agent (OPA).
 * The data is collected from the original REST request and the Keycloak JWT.
 */
public record OpaRequest(Input input) {
    public OpaRequest(HttpMethod method, String path, List<String> roles, List<String> groups) {
        this(new Input(method.name(), path, roles, groups));
    }

    record Input(String method, String path, List<String> roles, List<String> groups) {}
}
