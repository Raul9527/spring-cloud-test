package com.lrg.producer.controller.impl;

import com.lrg.api.controller.ConsumerController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerControllerImpl implements ConsumerController {


    public String getName(@PathVariable String name) {
        return name+"jj";
    }
}
