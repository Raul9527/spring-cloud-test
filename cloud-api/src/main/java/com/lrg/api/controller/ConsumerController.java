package com.lrg.api.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SPRING-CLOUD-PRODUCER")
public interface ConsumerController {

    @GetMapping("/name/{name}")
    String getName(@PathVariable String name);
}
