package com.lrg.consumer.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SPRING-CLOUD-PRODUCER",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(value = "name") String name);
}
