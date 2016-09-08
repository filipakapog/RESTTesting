package com.example;

/**
 * Created by bcaramihai on 9/8/2016.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;


//@Controller                 //RestController only returns strings or json | Controller returns webpages(html and so on)

@RestController
public class Hello {

    @RequestMapping("/")
    public String index() {
        return "Spring Boot dude";
    }

}
