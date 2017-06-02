package com.web.api.eircode.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


/**
 * Created by Flavio on 23/05/2017.
 */
@Repository
class AddressRepositoryImpl implements AddressRepositor {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String get(final String key) {
        return (String) this.redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(final String key, final String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }
}
