package com.lrg.consumer.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemote {
	public String hello(@RequestParam("name") String name) {
		return "hello" +name+", this messge send failed ";
	}
}
