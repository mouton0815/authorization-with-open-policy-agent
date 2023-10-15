package com.example.authz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpMethod;

import java.util.Set;

/**
 * POST request body to be sent to the Open Policy Agent (OPA).
 * The data is collected from the original REST request and the Keycloak JWT.
 */
public class OpaRequestData {
    static private class Input {
        private final String method;
        private final String path;
        private final Set<String> roles;
        @JsonProperty("company_id")
        private final String companyId;

        Input(HttpMethod httpMethod, String path, Set<String> roles, String companyId) {
            this.method = httpMethod.name();
            this.path = path;
            this.roles = roles;
            this.companyId = companyId;
        }

        public String getMethod() {
            return method;
        }

        public String getPath() {
            return path;
        }

        public Set<String> getRoles() {
            return roles;
        }

        public String getCompanyId() {
            return companyId;
        }
    }

    private final Input input;

    public OpaRequestData(HttpMethod httpMethod, String path, Set<String> roles, String companyId) {
        this.input = new Input(httpMethod, path, roles, companyId);
    }

    public Input getInput() {
        return input;
    }
}
