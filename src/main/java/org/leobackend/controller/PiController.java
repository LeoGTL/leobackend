package org.leobackend.controller;

import org.leobackend.entity.ResultEntity;
import org.leobackend.utils.MqttManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020-12-27.
 * 树莓派相关
 *
 * @author Leo
 */
@RestController
@RequestMapping ("/api/pi")
public class PiController {

    private MqttManager mqttManager;

    @Autowired
    public PiController (MqttManager mqttManager) {
        this.mqttManager = mqttManager;
    }

    @GetMapping ("/send")
    public ResultEntity<Object> send (String payload) {
        boolean isPublished = mqttManager.publish("leo_mqtt_test_topic", payload);
        return isPublished ? ResultEntity.success() : ResultEntity.failed();
    }

    @GetMapping ("/receive")
    public ResultEntity<Object> receive () {
        boolean isReceived = mqttManager.receive("leo_mqtt_test_topic");
        return isReceived ? ResultEntity.success() : ResultEntity.failed();
    }

}
