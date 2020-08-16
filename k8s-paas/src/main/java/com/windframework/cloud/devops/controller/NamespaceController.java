package com.windframework.cloud.devops.controller;

import com.windframework.cloud.devops.service.K8sNamespaceService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/ns")
public class NamespaceController {
    @Autowired
    K8sNamespaceService service;

    @GetMapping("/list")
    public NamespaceList list() {
        return service.list();
    }

    @GetMapping("/list/{nsName}")
    public Namespace list(@PathVariable String nsName) {
        return service.list(nsName);
    }

    @GetMapping("/add/{nsName}")
    public Namespace add(@PathVariable String nsName) {
        log.info("nsName:{}",nsName);
        return service.add(nsName);
    }

}
