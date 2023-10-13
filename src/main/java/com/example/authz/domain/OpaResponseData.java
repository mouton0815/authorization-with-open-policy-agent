package com.example.authz.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * POST response body to be received from the Open Policy Agent (OPA).
 */
public class OpaResponseData {
    private final boolean result;

    @JsonCreator
    public OpaResponseData(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
