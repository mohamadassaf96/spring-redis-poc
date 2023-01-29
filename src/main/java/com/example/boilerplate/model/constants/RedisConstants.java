package com.example.boilerplate.model.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RedisConstants {
    public static final String PERSON_DETAILS_CACHE = "personDetailsCache";
    public static final int SHORT_TERM_CACHE_TTL_SECONDS = 30;
}
