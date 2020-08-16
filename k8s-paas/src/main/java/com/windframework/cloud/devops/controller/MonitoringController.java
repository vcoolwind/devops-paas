package com.windframework.cloud.devops.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.windframework.cloud.devops.service.MonitoringService;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/monitoring")
public class MonitoringController {
    @Autowired
    MonitoringService service;

    @GetMapping("/crds")
    public CustomResourceDefinitionList showCRDs() {
        return service.showCRDs();
    }

    @GetMapping("/prometheusrule/list")
    public Map<String, Object> listPrometheusRule() {
        return service.listPrometheusRule();
    }

    @GetMapping("/prometheusrule/list/{nsName}")
    public Map<String, Object> listPrometheusRule(@PathVariable String nsName) {
        return service.listPrometheusRule(nsName);
    }

    @GetMapping("/prometheusrule/list/{nsName}/{prometheusRuleName}")
    public Map<String, Object> listPrometheusRule(@PathVariable String nsName, @PathVariable String prometheusRuleName) {
        return service.listPrometheusRule(nsName, prometheusRuleName);
    }

    @GetMapping("/prometheusrule/add/{nsName}/{prometheusRuleContentBase64}")
    public Map<String, Object> addPrometheusRule(@PathVariable String nsName, @PathVariable String prometheusRuleContentBase64) throws IOException {
        //mock here
        byte[] inbytes = IoUtil.readBytes(MonitoringController.class.getClassLoader()
                .getResourceAsStream("/prometheus-rules-test.yaml"));
        prometheusRuleContentBase64 = Base64.encode(inbytes, "utf-8");
        return service.addPrometheusRule(nsName, prometheusRuleContentBase64);
    }

}