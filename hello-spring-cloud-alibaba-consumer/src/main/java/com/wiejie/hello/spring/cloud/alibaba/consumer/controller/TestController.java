package com.wiejie.hello.spring.cloud.alibaba.consumer.controller;

import com.wiejie.hello.spring.cloud.alibaba.consumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RefreshScope
@RestController
public class TestController {

    private final RestTemplate restTemplate;

    @Resource
    private EchoService echoService;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/echo/{keyword}")
    public String echo(@PathVariable(value = "keyword") String keyword) {
        return restTemplate.getForObject("http://service-provider/echo/" + keyword, String.class);
    }

    @GetMapping(value = "/feign/echo/{keyword}")
    public String echoFeign(@PathVariable(value = "keyword") String keyword) {
        return echoService.echo(keyword);
    }

}
