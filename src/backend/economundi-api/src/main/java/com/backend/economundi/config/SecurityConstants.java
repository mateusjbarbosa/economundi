/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.economundi.config;

/**
 *
 * @author Alexandre
 */
public class SecurityConstants {

    // Authorization Bearer uheauhehgy3u231uh
    public static final String SECRET = "economundi";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final long EXPIRATION_TIME = 86400000L;
}
