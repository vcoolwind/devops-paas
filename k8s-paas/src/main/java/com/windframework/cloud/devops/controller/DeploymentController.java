package com.windframework.cloud.devops.controller;

import com.windframework.cloud.devops.service.K8SDeploymentService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/deploy")
public class DeploymentController {
    @Autowired
    K8SDeploymentService service;

    @GetMapping("/list")
    public DeploymentList list() {
        return service.list();
    }

    @GetMapping("/list/{nsName}")
    public DeploymentList list(@PathVariable String nsName) {
        return service.list(nsName);
    }

    @GetMapping("/add/{nsName}/{deployName}/{imageUrl}")
    public Deployment add(@PathVariable String nsName, @PathVariable String deployName, @PathVariable String imageUrl) {
        return service.add(nsName,deployName,  imageUrl);
    }

    @GetMapping("/update/{nsName}/{deployName}/{imageUrl}")
    public Deployment update(@PathVariable String nsName, @PathVariable String deployName, @PathVariable String imageUrl) {
        return service.update(nsName,deployName,  imageUrl);
    }

    @GetMapping("/delete/{nsName}/{deployName}")
    public Boolean delete(@PathVariable String nsName, @PathVariable String deployName) {
        return service.delete(nsName,deployName);
    }
}
