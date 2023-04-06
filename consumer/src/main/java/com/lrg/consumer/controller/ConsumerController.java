package com.lrg.consumer.controller;

import com.lrg.consumer.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
	@Autowired
	HelloRemote HelloRemote;

	@Autowired
	com.lrg.api.controller.ConsumerController consumerController;

	@RequestMapping("/hello/{name}")
	public String index(@PathVariable("name") String name) {
		return HelloRemote.hello(name);
	}

	@RequestMapping("/name/{name}")
	public String getName(@PathVariable("name") String name) {
		return consumerController.getName(name);
	}
}
