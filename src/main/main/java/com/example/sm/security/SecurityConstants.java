package com.example.sm.security;

public class SecurityConstants {

    public static final String SECRET = "LINDI_SECRET_KEY_AT_APA";
    public static final long EXPIRATION_TIME = 900_000; // 15 minutes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String CLAIM_KEY = "authorities";
}