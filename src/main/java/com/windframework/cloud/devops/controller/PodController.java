package com.windframework.cloud.devops.controller;

import com.windframework.cloud.devops.service.K8SPodService;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pod")
public class PodController {
    @Autowired
    K8SPodService service;

    @GetMapping("/list/{nsName}/{deployName}")
    public List<Pod> list(@PathVariable String nsName, @PathVariable String deployName) {
        return service.list(nsName,deployName);
    }

    @GetMapping("/delete/{nsName}/{deployName}")
    public Boolean delete(@PathVariable String nsName, @PathVariable String deployName) {
        return service.delete(nsName,deployName);
    }
}
