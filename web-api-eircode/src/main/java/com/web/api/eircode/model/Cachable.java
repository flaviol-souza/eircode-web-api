package com.web.api.eircode.model;

import java.io.Serializable;

/**
 * Created by Flavio on 23/05/2017.
 */
public interface Cachable extends Serializable {

    public String getKey();

    public String getValue();
}
