package com.example.authz.utils;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Helper class to extract data from Keycloak JWTs.
 * Due to the nested claims the functions only work for Keycloak JWTs and Spring's default JWT parser (Nimbus JWT).
 * All that is unfortunately rather fragile.
 */
public class JwtUtil {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Set<String> extractRoles(JwtAuthenticationToken token) {
        Set<String> roles = new TreeSet<>();
        // Realm roles are encoded as follows: realm_access: {roles: [api-read, offline_access, ...]}
        Map realmAccess = (Map) token.getTokenAttributes().get("realm_access");
        if (realmAccess != null) {
            List<String> realmRoles = (List<String>) realmAccess.get("roles");
            if (realmRoles != null) {
                roles.addAll(realmRoles);
            }
        }
        // Resource roles are encoded as follows: resource_access: {account: {roles: [view-profile, ...]}}
        Map resourceAccess = (Map) token.getTokenAttributes().get("resource_access");
        if (resourceAccess != null) {
            Map resourceAccount = (Map) resourceAccess.get("account");
            if (resourceAccount != null) {
                List<String> resourceRoles = (List<String>) resourceAccount.get("roles");
                if (resourceRoles != null) {
                    roles.addAll(resourceRoles);
                }
            }
        }
        return roles;
    }

    public static String extractCompanyId(JwtAuthenticationToken token) {
        String companyId = (String) token.getTokenAttributes().get("company_id");
        if (companyId == null) {
            throw new RuntimeException("Token claim 'company_id' missing");
        }
        return companyId;
    }
}
