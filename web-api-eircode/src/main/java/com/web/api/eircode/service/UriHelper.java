package com.web.api.eircode.service;

import com.web.api.eircode.exception.ApiKeyInvalidException;
import com.web.api.eircode.exception.ParameterRequiredException;
import com.web.api.eircode.exception.PostCoderRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Flavio on 22/05/2017.
 */
@Component
public final class UriHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${postcoder.host}")
    private String postcoderHost;

    @Value("${postcoder.service.address}")
    private String postcoderServiceAddress;

    public URI generateEircodeUri(final String apiKey, final String country, final String addressCode,
                                  final Integer lines, final String include, final String exclude, final String format,
                                  final String addtags, final String identifier, final String callback, final Integer page,
                                  final Boolean postcodeonly, final Boolean alias)
            throws ApiKeyInvalidException, ParameterRequiredException{

        if(apiKey == null || apiKey.trim().isEmpty()){
            throw new ApiKeyInvalidException("Api Key is required!");
        }

        if(country == null || country.trim().isEmpty() || addressCode == null || addressCode.trim().isEmpty()){
            throw new ParameterRequiredException("Country Code and Address Code are required!");
        }
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.postcoderHost + this.postcoderServiceAddress);

        // Add query parameter
        if (lines != null && lines > 0) builder.queryParam("lines",  lines);
        if (include != null && include.isEmpty()) builder.queryParam("include",  include);
        if (exclude != null && !exclude.isEmpty()) builder.queryParam("exclude",  exclude);
        if (format != null && !format.isEmpty()) builder.queryParam("format",  format);
        if (addtags != null && !addtags.isEmpty()) builder.queryParam("addtags",  addtags);
        if (identifier != null && !identifier.isEmpty()) builder.queryParam("identifier",  identifier);
        if (callback != null && !callback.isEmpty()) builder.queryParam("callback",  callback);
        if (page != null && page > 0) builder.queryParam("page",  page);
        if (postcodeonly != null) builder.queryParam("postcodeonly",  postcodeonly);
        if (alias != null) builder.queryParam("alias",  alias);

        // Add query path uri
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("api-key", apiKey);
        uriParams.put("country", country);
        uriParams.put("eircode-fragment", addressCode);

        return builder.buildAndExpand(uriParams).toUri();
    }

    public String involkeEircodeUri(final URI uri) throws PostCoderRequestException, ApiKeyInvalidException  {
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> request = new HttpEntity(null, headers);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

            if(HttpStatus.OK.equals(response.getStatusCode())){
                return response.getBody();
            }else{
                throw new PostCoderRequestException(response.getStatusCode(), response.getBody());
            }
        }catch (HttpClientErrorException e){
            if(HttpStatus.FORBIDDEN.equals(e.getStatusCode())){
                LOGGER.error("Incorrect Search Key", e);
                throw new ApiKeyInvalidException("Incorrect Search Key");
            }else if(HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                LOGGER.error("Unknown Country or Address", e);
                throw new PostCoderRequestException("Unknown Country or Address");
            }
        }

        return "";
    }
}
