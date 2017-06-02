package com.web.api.eircode.rest;

import com.web.api.eircode.exception.ApiKeyInvalidException;
import com.web.api.eircode.exception.ParameterRequiredException;
import com.web.api.eircode.exception.PostCoderRequestException;
import com.web.api.eircode.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Flavio on 22/05/2017.
 */
@RestController
@RequestMapping("/pcw")
public class AddressRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static final String REQUEST_XML = "xml";

    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping(path="/{apiKey}/address/{countryCode}/{addressCode}" , method=RequestMethod.GET)
    public ResponseEntity<String> address(final @PathVariable String apiKey,
                                          final @PathVariable String countryCode,
                                          final @PathVariable String addressCode,
                                          final @RequestParam(required=false) Integer lines,
                                          final @RequestParam(required=false) String include,
                                          final @RequestParam(required=false) String exclude,
                                          final @RequestParam(required=false) String format,
                                          final @RequestParam(required=false) String addtags,
                                          final @RequestParam(required=false) String identifier,
                                          final @RequestParam(required=false) String callback,
                                          final @RequestParam(required=false) Integer page,
                                          final @RequestParam(required=false) Boolean postcodeonly,
                                          final @RequestParam(required=false) Boolean alias) {
        LOGGER.info("get address country:{}, address:{}", countryCode, addressCode);
        final HttpHeaders httpHeaders = new HttpHeaders();
        try{
            if(AddressRestController.REQUEST_XML.equals(format)){
                httpHeaders.setContentType(MediaType.APPLICATION_XML);
            }else {
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            }

            if(apiKey == null || apiKey.trim().isEmpty()){
                throw new ApiKeyInvalidException("Api Key is required!");
            }

            if(countryCode == null || countryCode.trim().isEmpty() || addressCode == null || addressCode.trim().isEmpty()){
                throw new ParameterRequiredException("Country Code and Address Code are required!");
            }

            final String body = this.addressService.address(apiKey, countryCode, addressCode,
                    lines, include, exclude, format, addtags, identifier, callback, page, postcodeonly, alias);

            return new ResponseEntity<String>(body, httpHeaders, HttpStatus.OK);
        }catch (PostCoderRequestException e){
            LOGGER.error("address country:{}, address:{}, message:{}", countryCode, addressCode, e.getMessage(), e);
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (ApiKeyInvalidException e){
            LOGGER.error("Api Key Invalid! apiKey:{}, message:{}", apiKey, e.getMessage(), e);
            return new ResponseEntity<String>("Api Key Invalid!", httpHeaders, HttpStatus.UNAUTHORIZED);
        }catch (ParameterRequiredException e){
            LOGGER.error("address country:{}, address:{},  message:{}", countryCode, addressCode, e.getMessage(), e);
            return new ResponseEntity<String>("Country Code and Address Code are required!", httpHeaders, HttpStatus.BAD_REQUEST);
        }

    }
}
