package com.web.api.eircode.repository;

/**
 * Created by Flavio on 23/05/2017.
 */
public interface AddressRepositor  {

    String get(final String key);

    void set(final String key, final String value);
}
