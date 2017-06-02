package com.web.api.eircode.business;

import com.web.api.eircode.model.Address;
import com.web.api.eircode.repository.AddressRepositor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by Flavio on 23/05/2017.
 */
@Component
class AddressBusinessImpl implements AddressBusiness{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

   @Autowired
    private AddressRepositor addressRepository;

    @Override
    @CachePut(value = "address", keyGenerator="keyGenerator")
    public void put(final Address address) {
        LOGGER.info("put address key:{}, value:{}", address.getKey(), address.getValue());
        this.addressRepository.set(address.getKey(), address.getValue());
    }

    @Override
    public Address get(final Address address) {
        LOGGER.info("get address {}", address.getKey());
        final String fragmenteAddress = this.addressRepository.get(address.getKey());
        LOGGER.info("retrive fragment {}", address.getValue());
        address.setAddressFragment(fragmenteAddress);
        return address;
    }
}
