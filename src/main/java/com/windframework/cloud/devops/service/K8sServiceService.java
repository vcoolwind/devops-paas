package com.windframework.cloud.devops.service;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Service
public class K8sServiceService {
    @Autowired
    KubernetesClient client;

    public ServiceList list() {
        ServiceList myServices = client.services()
                .list();
        return myServices;
    }

    public ServiceList list(String nsName) {
        if (StringUtils.isEmpty(nsName)) {
            nsName = "default";
        }
        ServiceList myNsServices = client.services()
                .inNamespace(nsName)
                .list();

        return myNsServices;
    }

    public Service list(String nsName, String svcName) {
        if (StringUtils.isEmpty(nsName)) {
            nsName = "default";
        }
        Service myService = client.services()
                .inNamespace(nsName)
                .withName(svcName)
                .get();

        return myService;
    }

}
