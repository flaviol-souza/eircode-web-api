package com.web.api.eircode.service;

import com.web.api.eircode.business.AddressBusiness;
import com.web.api.eircode.exception.ApiKeyInvalidException;
import com.web.api.eircode.exception.ParameterRequiredException;
import com.web.api.eircode.exception.PostCoderRequestException;
import com.web.api.eircode.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by Flavio on 22/05/2017.
 */
@Component("addressService")
public class AddressServiceImpl implements AddressService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UriHelper uriHelper;

    @Autowired
    private AddressBusiness addressBusiness;

    @Override
    @Cacheable(value="address")
    public String address(final String apiKey, final String country, final String addressCode,
                   final Integer lines, final String include, final String exclude, final String format,
                   final String addtags, final String identifier, final String callback, final Integer page,
                   final Boolean postcodeonly, final Boolean alias)
            throws PostCoderRequestException, ParameterRequiredException, ApiKeyInvalidException {

        Address address = new Address.Builder()
                .withAddressCode(addressCode)
                .withAddtags(addtags)
                .withAlias(alias)
                .withCallback(callback)
                .withCountry(country)
                .withExclude(exclude)
                .withFormat(format)
                .withIdentifier(identifier)
                .withInclude(include)
                .withLines(lines)
                .withPage(page)
                .withPostcodeonly(postcodeonly)
                .build();

        address = this.addressBusiness.get(address);
        if(address.getAddressFragment() == null || address.getAddressFragment().isEmpty()){
            final String addressFragment  = this.requestAddress(apiKey, country, addressCode,
                    lines, include, exclude, format, addtags, identifier, callback, page, postcodeonly, alias);
            address.setAddressFragment(addressFragment);
            this.addressBusiness.put(address);
        }

        return address.getAddressFragment();
    }

    private String requestAddress(final String apiKey, final String country, final String addressCode,
                                  final Integer lines, final String include, final String exclude, final String format,
                                  final String addtags, final String identifier, final String callback, final Integer page,
                                  final Boolean postcodeonly, final Boolean alias)
            throws PostCoderRequestException, ParameterRequiredException, ApiKeyInvalidException {
        LOGGER.info("Request data in API country:{} address:{}", country, addressCode);
        final URI uri = this.uriHelper.generateEircodeUri(apiKey, country, addressCode,
                lines, include, exclude, format, addtags, identifier, callback, page, postcodeonly, alias);
        final String response = this.uriHelper.involkeEircodeUri(uri);
        LOGGER.info(response);
        return response;
    }
}
