package com.web.api.eircode.service;

import com.web.api.eircode.exception.ApiKeyInvalidException;
import com.web.api.eircode.exception.ParameterRequiredException;
import com.web.api.eircode.exception.PostCoderRequestException;

/**
 * Created by Flavio on 22/05/2017.
 */
public interface AddressService {

    String address(final String apiKey, final String countryCode, final String addressCode,
                   final Integer lines, final String include, final String exclude, final String format,
                   final String addtags, final String identifier, final String callback, final Integer page,
                   final Boolean postcodeonly, final Boolean alias)
            throws PostCoderRequestException, ParameterRequiredException, ApiKeyInvalidException;
}
