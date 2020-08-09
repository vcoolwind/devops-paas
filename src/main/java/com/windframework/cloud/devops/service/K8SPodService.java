package com.windframework.cloud.devops.service;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class K8SPodService {
    @Autowired
    KubernetesClient client;

    public Boolean delete(String nsName, String deployName) {
        PodList podList;
        podList = client.pods().inNamespace(nsName).withLabel("k8s-app", deployName).list();
        if (podList != null && podList.getItems().size() > 0) {
            return client.pods().inNamespace(nsName).withLabel("k8s-app", deployName).delete();
        } else {
            return client.pods().inNamespace(nsName).withLabel("app", deployName).delete();
        }
    }

    public List<Pod> list(String nsName, String deployName) {
        PodList podList;
        podList = client.pods().inNamespace(nsName).withLabel("k8s-app", deployName).list();
        if (podList != null && podList.getItems().size() > 0) {
            return client.pods().inNamespace(nsName).withLabel("k8s-app", deployName).list().getItems();
        } else {
            return client.pods().inNamespace(nsName).withLabel("app", deployName).list().getItems();
        }
    }
}
