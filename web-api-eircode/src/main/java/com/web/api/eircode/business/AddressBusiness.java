package com.web.api.eircode.business;

import com.web.api.eircode.model.Address;

/**
 * Created by Flavio on 23/05/2017.
 */
public interface AddressBusiness  {
    void put(Address address);

    Address get(Address address);
}
