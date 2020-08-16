package com.windframework.cloud.devops.config;

import io.fabric8.kubernetes.client.AutoAdaptableKubernetesClient;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.windframework.cloud.devops.config.Constants.KUBERNETES_NAMESPACE;
import static com.windframework.cloud.devops.config.Constants.RUN_IN_K8S;
import static io.fabric8.kubernetes.client.Config.KUBERNETES_TRUST_CERT_SYSTEM_PROPERTY;

/**
 * desc:
 *
 * @author 王彦锋
 * @date 2020/2/22 12:00
 */
@Slf4j
@Configuration
public class Fabric8ioConfig {
    @Value("${k8sconfig.url}")
    private String k8sUrl;

    @Value("${k8sconfig.token}")
    private String k8sToken;

    @Bean
    public KubernetesClient getClient() {

        System.getProperties().put(KUBERNETES_TRUST_CERT_SYSTEM_PROPERTY, "true");
        ConfigBuilder builder = new ConfigBuilder().withTrustCerts(true);
        if (System.getenv().containsKey(RUN_IN_K8S) || System.getenv().containsKey(KUBERNETES_NAMESPACE)) {
            log.info("run in K8S cluster!");
        } else {
            log.info("run in remote");
            log.info("getClientByToken now -- k8sUrl: {}", k8sUrl);
            log.info("getClientByToken now -- k8sToken: {}", k8sToken);
            builder.withMasterUrl(k8sUrl).withOauthToken(k8sToken);
        }

        KubernetesClient client = new AutoAdaptableKubernetesClient(builder.build());
        return client;
    }

}
