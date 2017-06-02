package com.web.api.eircode.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Flavio on 22/05/2017.
 */
@RestController
//@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/1")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}