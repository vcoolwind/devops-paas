package com.windframework.cloud.devops.controller;

import com.windframework.cloud.devops.service.K8sServiceService;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/svc")
public class ServiceController {
    @Autowired
    K8sServiceService service;

    @GetMapping("/list")
    public ServiceList list() {
        return service.list();
    }

    @GetMapping("/list/ns/{nsName}")
    public ServiceList list(@PathVariable String nsName) {
        return service.list(nsName);
    }

    @GetMapping("/list/ns/{nsName}/svc/{svcName}")
    public Service list(@PathVariable String nsName, @PathVariable String svcName) {
        return service.list(nsName, svcName);
    }
}
