package com.windframework.cloud.devops.service;

import cn.hutool.core.codec.Base64Decoder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MonitoringService {
    @Autowired
    KubernetesClient client;
    private static CustomResourceDefinitionContext prometheusRulesContext;

    static {
        prometheusRulesContext = new CustomResourceDefinitionContext.Builder()
                .withGroup("monitoring.coreos.com")
                .withPlural("prometheusrules")
                .withScope("Namespaced")
                .withVersion("v1")
                .build();
    }

    public CustomResourceDefinitionList showCRDs() {
        CustomResourceDefinitionList crds = client.customResourceDefinitions().list();
        List<CustomResourceDefinition> crdsItems = crds.getItems();

        log.info("Found " + crdsItems.size() + " CRD(s)");
        for (CustomResourceDefinition crd : crdsItems) {
            ObjectMeta metadata = crd.getMetadata();
            if (metadata != null) {
                String name = metadata.getName();
                log.info("    " + name + " => " + metadata.getSelfLink());
            }
        }
        return crds;
    }

    public Map<String, Object> listPrometheusRule() {
        return client.customResource(prometheusRulesContext).list();
    }

    public Map<String, Object> listPrometheusRule(String nsName) {
        return client.customResource(prometheusRulesContext).list(nsName);
    }

    public Map<String, Object> listPrometheusRule(String nsName, String prometheusRuleName) {
        return client.customResource(prometheusRulesContext).get(nsName, prometheusRuleName);
    }

    public Map<String, Object> addPrometheusRule(String nsName, String prometheusRuleContentBase64) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(Base64Decoder.decode(prometheusRuleContentBase64,"utf-8"));
        return client.customResource(prometheusRulesContext).create(nsName, bis);
    }
}
