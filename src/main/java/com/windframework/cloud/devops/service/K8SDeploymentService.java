package com.windframework.cloud.devops.service;

import cn.hutool.core.codec.Base64Decoder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class K8SDeploymentService {
    @Autowired
    KubernetesClient client;

    public DeploymentList list() {
        return client.apps().deployments().list();
    }

    public DeploymentList list(String nsName) {
        return client.apps().deployments().inNamespace(nsName).list();
    }

    public Deployment get(String nsName, String deployName) {
        Deployment deployment = client.apps().deployments().inNamespace(nsName).withName(deployName).get();
        return deployment;
    }

    /**
     * 更新Deployment
     *
     * @param nsName
     * @param deployName
     * @param imageUrl
     */
    public Deployment update(String nsName, String deployName, String imageUrl) {
        String goalImageUrl = Base64Decoder.decodeStr(imageUrl);
        Deployment deployment = get(nsName, deployName);
        deployment.getSpec().getTemplate().getSpec().getContainers().get(0).setImage(goalImageUrl);
        Deployment orReplace = client.apps().deployments().inNamespace(nsName).createOrReplace(deployment);
        return orReplace;
    }

    /**
     * 添加Deployment
     *
     * @param deployName
     * @param imageUrl
     * @param nsName
     * @return
     */
    public Deployment add(String nsName, String deployName, String imageUrl) {
        String goalImageUrl = Base64Decoder.decodeStr(imageUrl);
        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                .withName(deployName)
                .endMetadata()
                .withNewSpec()
                .withReplicas(1)
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels("app", deployName)
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName(deployName)
                .withImage(goalImageUrl)
                .addNewPort()
                .withContainerPort(8000)
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .withNewSelector()
                .addToMatchLabels("app", deployName)
                .endSelector()
                .endSpec()
                .build();
        return client.apps().deployments().inNamespace(nsName).create(deployment);

    }

}
