package com.web.api.eircode.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Flavio on 23/05/2017.
 */
public class Address implements Cachable {

    private String country;
    private String addressCode;
    private Integer lines;
    private String include;
    private String exclude;
    private String format;
    private String addtags;
    private String identifier;
    private String callback;
    private Integer page;
    private Boolean postcodeonly;
    private Boolean alias;
    private String addressFragment;

    @Override
    public String getKey() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.hashCode());
        return  sb.toString();
    }

    @Override
    public String getValue() {
        return this.getAddressFragment();
    }

    public Address() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAddtags() {
        return addtags;
    }

    public void setAddtags(String addtags) {
        this.addtags = addtags;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getPostcodeonly() {
        return postcodeonly;
    }

    public void setPostcodeonly(Boolean postcodeonly) {
        this.postcodeonly = postcodeonly;
    }

    public Boolean getAlias() {
        return alias;
    }

    public void setAlias(Boolean alias) {
        this.alias = alias;
    }

    public String getAddressFragment() {
        return addressFragment;
    }

    public void setAddressFragment(String addressFragment) {
        this.addressFragment = addressFragment;
    }

    @Override
    //All attributes except addressFragment
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(addressCode, address.addressCode) &&
                Objects.equals(lines, address.lines) &&
                Objects.equals(include, address.include) &&
                Objects.equals(exclude, address.exclude) &&
                Objects.equals(format, address.format) &&
                Objects.equals(addtags, address.addtags) &&
                Objects.equals(identifier, address.identifier) &&
                Objects.equals(callback, address.callback) &&
                Objects.equals(page, address.page) &&
                Objects.equals(postcodeonly, address.postcodeonly) &&
                Objects.equals(alias, address.alias);
    }

    @Override
    //All attributes except addressFragment
    public int hashCode() {
        List<Object> hashObject = new ArrayList<Object>();

        if (country != null && country.isEmpty()) hashObject.add(country);
        if (addressCode != null && addressCode.isEmpty()) hashObject.add(addressCode);
        if (lines != null && lines > 0) hashObject.add(lines);
        if (include != null && include.isEmpty()) hashObject.add(include);
        if (exclude != null && !exclude.isEmpty()) hashObject.add(exclude);
        if (format != null && !format.isEmpty()) hashObject.add(format);
        if (addtags != null && !addtags.isEmpty()) hashObject.add(addtags);
        if (identifier != null && !identifier.isEmpty()) hashObject.add(identifier);
        if (callback != null && !callback.isEmpty()) hashObject.add(callback);
        if (page != null && page > 0) hashObject.add(page);
        if (postcodeonly != null) hashObject.add(postcodeonly);
        if (alias != null) hashObject.add(alias);
        return Objects.hash(hashObject.toArray());
    }

    public static class Builder {
        protected Address address;

        public Builder() {
            address = new Address();
        }

        public Builder withCountry(final String country) {
            address.country = country;
            return this;
        }

        public Builder withAddressCode(final String addressCode) {
            address.addressCode = addressCode;
            return this;
        }

        public Builder withLines(final Integer lines) {
            address.lines = lines;
            return this;
        }

        public Builder withInclude(final String include) {
            address.include = include;
            return this;
        }

        public Builder withExclude(final String exclude) {
            address.exclude = exclude;
            return this;
        }

        public Builder withFormat(final String format) {
            address.format = format;
            return this;
        }

        public Builder withAddtags(final String addtags) {
            address.addtags = addtags;
            return this;
        }

        public Builder withIdentifier(final String identifier) {
            address.identifier = identifier;
            return this;
        }

        public Builder withCallback(final String callback) {
            address.callback = callback;
            return this;
        }

        public Builder withPage(final Integer page) {
            address.page = page;
            return this;
        }

        public Builder withPostcodeonly(final Boolean postcodeonly) {
            address.postcodeonly = postcodeonly;
            return this;
        }

        public Builder withAlias(final Boolean alias) {
            address.alias = alias;
            return this;
        }

        public Builder withAddressFragment(final String addressFragment) {
            address.addressFragment = addressFragment;
            return this;
        }

        public Address build() {
            return address;
        }
    }
}
