package com.windframework.cloud.devops.service;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class K8sNamespaceService {
    @Autowired
    KubernetesClient client;

    public NamespaceList list() {
        NamespaceList namespaceList = client.namespaces()
                .list();
        return namespaceList;
    }

    public Namespace list(String nsName) {
        if (StringUtils.isEmpty(nsName)) {
            nsName = "default";
        }
        Namespace namespace = client.namespaces()
                .withName(nsName).
                        get();

        return namespace;
    }

    public Namespace add(String nsName) {
        log.info("nsName:{}", nsName);
        return client.namespaces().createNew()
                .withNewMetadata()
                .withName(nsName)
                .addToLabels("author", "wangyf")
                .endMetadata()
                .done();
    }

}
