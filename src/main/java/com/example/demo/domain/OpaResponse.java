package com.example.demo.domain;

/**
 * POST response body to be received from the Open Policy Agent (OPA).
 */
public record OpaResponse(boolean result) {}
