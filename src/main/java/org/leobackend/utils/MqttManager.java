package org.leobackend.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2021-01-03.
 * mqtt客户端
 *
 * @author Leo
 */
@Component
@ConfigurationProperties (prefix = "mqtt")
public class MqttManager {

    @Value("${mqtt.client.username}")
    private String username;
    @Value("${mqtt.client.password}")
    private String password;
    @Value("${mqtt.client.serverURI}")
    private String serverURI;
    @Value("${mqtt.client.clientId}")
    private String clientId;
    @Value("${mqtt.client.keepAliveInterval}")
    private int keepAliveInterval;
    @Value("${mqtt.client.connectionTimeout}")
    private int connectionTimeout;

    private MqttClient client;

    private MqttClient mqttClient () {
        if (client != null)
            return client;
        try {
            client = new MqttClient(serverURI, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(connectionTimeout);
            options.setKeepAliveInterval(keepAliveInterval);
            client.connect(options);
            boolean connected = client.isConnected();
            return connected ? client : null;
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close () {
        try {
            MqttClient client = this.mqttClient();
            assert client != null;
            client.close(true);
        } catch (MqttException e) {
            e.printStackTrace();
            client = null;
        }
    }

    public boolean publish (String topic, String payload) {
        MqttClient client = mqttClient();
        MqttMessage message = new MqttMessage();
        message.setPayload(payload.getBytes());
        message.setQos(2);
        try {
            assert client != null;
            client.setCallback(new MqttPushCallback());
            client.publish(topic, message);
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean receive (String topicFilter) {
        MqttClient client = mqttClient();
        try {
            assert client != null;
            client.setCallback(new MqttReceiveCallback());
            client.subscribe(topicFilter, 2);
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

}
