package com.jh.sinkerr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/sink")
public class SinkController {

    @Autowired
    private SinkService service;
	
    @GetMapping(value = "/show")
    @ResponseBody
    public List<Sink> show() {
        return service.show();
    }
    
    @PatchMapping(value = "/fix")
    @ResponseBody
    public Sink fix(@RequestBody SinkDto message) throws JsonProcessingException {
        return service.fix(message);
    }
}
